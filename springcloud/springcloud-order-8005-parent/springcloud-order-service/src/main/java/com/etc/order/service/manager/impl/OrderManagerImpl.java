package com.etc.order.service.manager.impl;

import com.etc.order.repository.dao.OrderDao;
import com.etc.order.repository.dao.OrderItemDao;
import com.etc.order.repository.entity.MasterOrder;
import com.etc.order.repository.entity.OrderItem;
import com.etc.order.repository.vo.OrderVo;
import com.etc.order.service.bo.OrderBo;
import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.PlaseOrderBo;
import com.etc.order.service.bo.ReturnResultBo;
import com.etc.order.service.convert.OrderBoConvert;
import com.etc.order.service.emuns.MasterOrderEmuns;
import com.etc.order.service.listener.event.PayResultEvent;
import com.etc.order.service.manager.InventoryManager;
import com.etc.order.service.manager.OrderManager;
import com.etc.order.service.returnResult.PayResult;
import com.etc.order.service.utils.RedisUtil;
import com.etc.order.service.utils.UUIDUtils;
import com.etc.pay.client.PayClient;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * 订单服务类
 * @author Yuxiang Liu
 * */
@Service
public class OrderManagerImpl implements OrderManager {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private InventoryManager inventoryManager;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PayClient payClient;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private OrderBoConvert orderBoConvert;

    @Override
    public PageInfo<OrderBo> queryOrderBySku(String skuId,String buyerId, Integer pageNo, Integer pageSize) {
        if(!Optional.ofNullable(skuId).isPresent() && !Optional.ofNullable(buyerId).isPresent()){
            throw new RuntimeException("入参校验失败！");
        }
        PageInfo<OrderVo> pageInfo= orderDao.queryOrderBySku(skuId,buyerId,pageNo,pageSize);
        return orderBoConvert.toPageInfoOrderBo(pageInfo);
    }


    /**
     * 提交订单
     * */
    @Override
    @Transactional
    public ReturnResultBo placeOrder(PlaseOrderBo plaseOrderBo) {
        try{
            this.verifyPlaceOrder(plaseOrderBo);//参数校验
            // 1. 扣减库存（仅在缓存中）
            boolean success = inventoryManager.deductInventory(plaseOrderBo.getProductId(), plaseOrderBo.getQuantity());
            if (!success) {
                throw new RuntimeException("库存不足，下单失败");
            }
            PayResult payResult=addOrder(plaseOrderBo);// 创建订单
            return ReturnResultBo.success("下单成功,请支付",payResult);
        }catch (Exception e){
            this.rollbackInventory(plaseOrderBo.getProductId(),plaseOrderBo.getQuantity());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnResultBo.fail("程序异常",e.getMessage());
        }
    }


    /**
     * redis库存回滚
     */
    private void rollbackInventory(String  productId, Integer quantity) {
        // 更新库存逻辑，这里只模拟更新缓存中的库存
        int num= (int) redisUtil.get(productId);
        redisUtil.set(productId, num + quantity);
    }


    /**
     * 参数校验
     * */
    private void verifyPlaceOrder(PlaseOrderBo plaseOrderBo){
        if(!Optional.ofNullable(plaseOrderBo).isPresent()){
            throw new RuntimeException("请求体入参为空");
        }
        if(!Optional.ofNullable(plaseOrderBo.getProductId()).isPresent()){
            throw new RuntimeException("商品id为空，请核查参数");
        }
        if(!Optional.ofNullable(plaseOrderBo.getBuyerId()).isPresent()){
            throw new RuntimeException("买家id为空，请核查参数");
        }
        if(!Optional.ofNullable(plaseOrderBo.getSellerId()).isPresent()){
            throw new RuntimeException("卖家id为空，请核查参数");
        }
        if(!Optional.ofNullable(plaseOrderBo.getPrice()).isPresent()){
            throw new RuntimeException("商品单价为空，请核查参数");
        }
        if(!Optional.ofNullable(plaseOrderBo.getQuantity()).isPresent()){
            throw new RuntimeException("购买商品数量为空，请核查参数");
        }
        if(!Optional.ofNullable(plaseOrderBo.getSkuId()).isPresent()){
            throw new RuntimeException("商品SKUID为空，请核查参数");
        }

    }

    /**
     * 创建订单
     * */
    private PayResult addOrder(PlaseOrderBo plaseOrderBo){
        MasterOrder masterOrder=new MasterOrder();
        masterOrder.setOrderId(UUIDUtils.getUUID());
        masterOrder.setCreateTime(new Date());
        masterOrder.setBuyerId(plaseOrderBo.getBuyerId());
        masterOrder.setSellerId(plaseOrderBo.getSellerId());
        masterOrder.setSkuId(plaseOrderBo.getSkuId());
        masterOrder.setTotalPrice(plaseOrderBo.getPrice()*plaseOrderBo.getQuantity());
        masterOrder.setRemark(plaseOrderBo.getRemark());
        masterOrder.setOrderStatus(MasterOrderEmuns.ORDER_STATUS_PUB1.getValue());
        orderDao.createOrder(masterOrder);
        this.addOrderItem(plaseOrderBo,masterOrder);// 创建订单明细
        return new PayResult(masterOrder.getOrderId(),masterOrder.getBuyerId(), masterOrder.getSellerId(), masterOrder.getTotalPrice());

    }

    /**
     * 创建订单明细
     * */
    private Integer addOrderItem(PlaseOrderBo plaseOrderBo,MasterOrder masterOrder){
        OrderItem orderItem=new OrderItem();
        orderItem.setOrderItemId(UUIDUtils.getUUID());
        orderItem.setCreateTime(new Date());
        orderItem.setOrderId(masterOrder.getOrderId());
        orderItem.setProductId(plaseOrderBo.getProductId());
        orderItem.setPrice(plaseOrderBo.getPrice());
        orderItem.setQuantity(plaseOrderBo.getQuantity());
        return orderItemDao.addOrderItem(orderItem);
    }


    @Override
    @Transactional
    public ReturnResultBo payOrder(PayOrderBo payOrderBo) throws Exception {
        try{
            this.verifyPayOrder(payOrderBo);
            //校验订单状态，避免重复支付
            MasterOrder masterOrder=orderDao.queryByOrderId(payOrderBo.getOrderId(), payOrderBo.getBuyerId(), MasterOrderEmuns.ORDER_STATUS_PUB2.getValue());
            if(Optional.ofNullable(masterOrder).isPresent()){
                return ReturnResultBo.success("订单已经支付成功，请勿重复发起支付，避免误扣金额！",payOrderBo);
            }
            Boolean flag=payClient.pay(payOrderBo.getTotalPrice(),payOrderBo.getOrderId());//调用支付接口
            //支付策略回调刷新订单状态，采用异步处理
            applicationEventPublisher.publishEvent(new PayResultEvent(flag,payOrderBo));
            if(Boolean.FALSE.equals(flag)){
                return ReturnResultBo.fail("支付接口异常，支付接口返回状态："+flag,payOrderBo);
            }
            return ReturnResultBo.success("支付成功",payOrderBo);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnResultBo.fail("支付接口异常："+e.getMessage(),payOrderBo);
        }
    }


    /**
     * 参数校验
     * */
    private void verifyPayOrder(PayOrderBo payOrderBo){
        if(!Optional.ofNullable(payOrderBo).isPresent()){
            throw new RuntimeException("请求体入参为空");
        }
        if(!Optional.ofNullable(payOrderBo.getOrderId()).isPresent()){
            throw new RuntimeException("订单ID为空，请核查参数");
        }
        if(!Optional.ofNullable(payOrderBo.getTotalPrice()).isPresent()){
            throw new RuntimeException("总价为空，请核查参数");
        }
        if(!Optional.ofNullable(payOrderBo.getBuyerId()).isPresent()){
            throw new RuntimeException("买家id为空，请核查参数");
        }
        if(!Optional.ofNullable(payOrderBo.getSellerId()).isPresent()){
            throw new RuntimeException("卖家id为空，请核查参数");
        }
    }



}
