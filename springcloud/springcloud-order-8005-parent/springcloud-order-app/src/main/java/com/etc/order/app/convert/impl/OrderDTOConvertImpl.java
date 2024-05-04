package com.etc.order.app.convert.impl;

import com.etc.order.api.Result.ReturnResult;
import com.etc.order.api.dto.MasterOrderDTO;
import com.etc.order.api.dto.OrderDTO;
import com.etc.order.api.dto.PayOrderDTO;
import com.etc.order.api.dto.PlaseOrderDTO;
import com.etc.order.app.convert.OrderDTOConvert;
import com.etc.order.repository.entity.MasterOrder;
import com.etc.order.service.bo.OrderBo;
import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.PlaseOrderBo;
import com.etc.order.service.bo.ReturnResultBo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderDTOConvertImpl implements OrderDTOConvert {
    @Override
    public List<MasterOrderDTO> toDTOList(List<MasterOrder> masterOrderList) {
        if ( masterOrderList == null ) {
            return null;
        }

        List<MasterOrderDTO> list = new ArrayList<MasterOrderDTO>( masterOrderList.size() );
        for ( MasterOrder masterOrder : masterOrderList) {
            list.add( orderToOrderDTO(masterOrder) );
        }

        return list;
    }

    @Override
    public PageInfo<OrderDTO> toPageInfoOrderDto(PageInfo<OrderBo> pageInfo) {
        if(pageInfo == null ){
            return null;
        }
        PageInfo<OrderDTO> pageInfo1 = new PageInfo<OrderDTO>();

        pageInfo1.setPageNum( pageInfo.getPageNum() );
        pageInfo1.setList( toOrderDTOList( pageInfo.getList() ) );
        pageInfo1.setEndRow( pageInfo.getEndRow() );
        pageInfo1.setPages( pageInfo.getPages() );
        pageInfo1.setTotal( pageInfo.getTotal() );
        pageInfo1.setStartRow( pageInfo.getStartRow() );
        pageInfo1.setPageSize( pageInfo.getPageSize() );
        pageInfo1.setNavigatePages( pageInfo.getNavigatePages() );

        return pageInfo1;

    }

    @Override
    public List<OrderDTO> toOrderDTOList(List<OrderBo> orderBoList) {
        if ( orderBoList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orderBoList.size() );
        for ( OrderBo orderBo : orderBoList) {
            list.add( toOrderDTO(orderBo) );
        }

        return list;
    }

    protected OrderDTO toOrderDTO(OrderBo orderBo) {
        if ( orderBo == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderBo.getOrderId());
        orderDTO.setBuyerId(orderBo.getBuyerId());
        orderDTO.setSellerId(orderBo.getSellerId());
        orderDTO.setTotalPrice(orderBo.getTotalPrice());
        orderDTO.setSkuId(orderBo.getSkuId());
        orderDTO.setOrderStatus(orderBo.getOrderStatus());
        orderDTO.setQuantity(orderBo.getQuantity());
        orderDTO.setPrice(orderBo.getPrice());

        return orderDTO;
    }

    @Override
    public MasterOrder toMasterOrder(MasterOrderDTO masterOrderDTO) {
        if ( masterOrderDTO == null ) {
            return null;
        }

        MasterOrder masterOrder = new MasterOrder();
        masterOrder.setUid(masterOrderDTO.getUid());
        masterOrder.setOrderId(masterOrderDTO.getOrderId());
        masterOrder.setBuyerId(masterOrderDTO.getBuyerId());
        masterOrder.setSellerId(masterOrderDTO.getSellerId());
        masterOrder.setTotalPrice(masterOrderDTO.getTotalPrice());
        masterOrder.setSkuId(masterOrderDTO.getSkuId());
        masterOrder.setCreateTime(masterOrderDTO.getCreateTime());
        masterOrder.setOrderStatus(masterOrderDTO.getOrderStatus());
        masterOrder.setRemark(masterOrderDTO.getRemark());

        return masterOrder;
    }



    protected MasterOrderDTO orderToOrderDTO(MasterOrder masterOrder) {
        if ( masterOrder == null ) {
            return null;
        }

        MasterOrderDTO masterOrderDTO = new MasterOrderDTO();
        masterOrderDTO.setUid(masterOrder.getUid());
        masterOrderDTO.setOrderId(masterOrder.getOrderId());
        masterOrderDTO.setBuyerId(masterOrder.getBuyerId());
        masterOrderDTO.setSellerId(masterOrder.getSellerId());
        masterOrderDTO.setTotalPrice(masterOrder.getTotalPrice());
        masterOrderDTO.setSkuId(masterOrder.getSkuId());
        masterOrderDTO.setCreateTime(masterOrder.getCreateTime());
        masterOrderDTO.setOrderStatus(masterOrder.getOrderStatus());
        masterOrderDTO.setRemark(masterOrder.getRemark());


        return masterOrderDTO;
    }


    @Override
    public PlaseOrderBo toPlaseOrderBo(PlaseOrderDTO plaseOrderDTO) {
        if ( plaseOrderDTO == null ) {
            return null;
        }

        PlaseOrderBo plaseOrderBo = new PlaseOrderBo();
        plaseOrderBo.setOrderId(plaseOrderDTO.getOrderId());
        plaseOrderBo.setBuyerId(plaseOrderDTO.getBuyerId());
        plaseOrderBo.setSellerId(plaseOrderDTO.getSellerId());
        plaseOrderBo.setProductId(plaseOrderDTO.getProductId());
        plaseOrderBo.setSkuId(plaseOrderDTO.getSkuId());
        plaseOrderBo.setPrice(plaseOrderDTO.getPrice());
        plaseOrderBo.setQuantity(plaseOrderDTO.getQuantity());
        plaseOrderBo.setRemark(plaseOrderDTO.getRemark());

        return plaseOrderBo;
    }

    @Override
    public ReturnResult toReturnResult(ReturnResultBo returnResultBo) {
        if ( returnResultBo == null ) {
            return null;
        }

        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(returnResultBo.getCode());
        returnResult.setMsg(returnResultBo.getMsg());
        returnResult.setDate(returnResultBo.getDate());

        return returnResult;
    }

    @Override
    public PayOrderBo toPayOrderBo(PayOrderDTO payOrderDTO) {
        if ( payOrderDTO == null ) {
            return null;
        }

        PayOrderBo payOrderBo = new PayOrderBo();
        payOrderBo.setOrderId(payOrderDTO.getOrderId());
        payOrderBo.setSellerId(payOrderDTO.getSellerId());
        payOrderBo.setBuyerId(payOrderDTO.getBuyerId());
        payOrderBo.setTotalPrice(payOrderDTO.getTotalPrice());

        return payOrderBo;
    }

}
