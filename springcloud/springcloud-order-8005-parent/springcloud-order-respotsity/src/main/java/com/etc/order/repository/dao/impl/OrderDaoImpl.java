package com.etc.order.repository.dao.impl;

import cn.hutool.core.collection.ListUtil;
import com.etc.order.repository.dao.OrderDao;
import com.etc.order.repository.entity.MasterOrder;
import com.etc.order.repository.mapper.OrderMapper;
import com.etc.order.repository.vo.OrderVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public PageInfo<OrderVo> queryOrderBySku(String skuId,String buyerId, Integer pageNo, Integer pageSize) {
        if(StringUtils.isEmpty(skuId) && StringUtils.isEmpty(buyerId)){
            return new PageInfo<>();
        }
        PageHelper.startPage(pageNo,pageSize);
        List<OrderVo> orderVos=orderMapper.queryOrderBySku(skuId,buyerId);
        PageInfo<OrderVo> pageInfo=new PageInfo<OrderVo>(orderVos);
        return pageInfo;
    }

    @Override
    public MasterOrder queryByOrderId(String orderId, String buyerId, String orderStatus) {
        if(StringUtils.isEmpty(orderId) || StringUtils.isEmpty(buyerId)){
            return null;
        }
        return orderMapper.queryByOrderId(orderId,buyerId,orderStatus);
    }

    @Override
    public Integer createOrder(MasterOrder masterOrder) {
        return orderMapper.createOrder(masterOrder);
    }

    @Override
    public Integer updateMasterOrder(String statusCd,String orderId) {
        if(StringUtils.isEmpty(statusCd) || StringUtils.isEmpty(orderId) ){
            return 0;
        }
        return orderMapper.updateMasterOrder(statusCd,orderId);
    }
}
