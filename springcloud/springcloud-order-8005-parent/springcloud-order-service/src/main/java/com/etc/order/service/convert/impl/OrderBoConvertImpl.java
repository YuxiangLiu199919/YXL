package com.etc.order.service.convert.impl;

import com.etc.order.repository.vo.OrderVo;
import com.etc.order.service.bo.OrderBo;
import com.etc.order.service.convert.OrderBoConvert;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderBoConvertImpl implements OrderBoConvert {

    @Override
    public PageInfo<OrderBo> toPageInfoOrderBo(PageInfo<OrderVo> pageInfo) {
        if(pageInfo == null ){
            return null;
        }
        PageInfo<OrderBo> pageInfo1 = new PageInfo<OrderBo>();

        pageInfo1.setPageNum( pageInfo.getPageNum() );
        pageInfo1.setList( toOrderBoList( pageInfo.getList() ) );
        pageInfo1.setEndRow( pageInfo.getEndRow() );
        pageInfo1.setPages( pageInfo.getPages() );
        pageInfo1.setTotal( pageInfo.getTotal() );
        pageInfo1.setStartRow( pageInfo.getStartRow() );
        pageInfo1.setPageSize( pageInfo.getPageSize() );
        pageInfo1.setNavigatePages( pageInfo.getNavigatePages() );

        return pageInfo1;

    }

    @Override
    public List<OrderBo> toOrderBoList(List<OrderVo> orderVoList) {
        if ( orderVoList == null ) {
            return null;
        }

        List<OrderBo> list = new ArrayList<OrderBo>( orderVoList.size() );
        for ( OrderVo orderVo : orderVoList) {
            list.add( toOrderBo(orderVo) );
        }

        return list;
    }

    protected OrderBo toOrderBo(OrderVo orderVo) {
        if ( orderVo == null ) {
            return null;
        }

        OrderBo orderBo = new OrderBo();
        orderBo.setOrderId(orderVo.getOrderId());
        orderBo.setBuyerId(orderVo.getBuyerId());
        orderBo.setSellerId(orderVo.getSellerId());
        orderBo.setTotalPrice(orderVo.getTotalPrice());
        orderBo.setSkuId(orderVo.getSkuId());
        orderBo.setOrderStatus(orderVo.getOrderStatus());
        orderBo.setQuantity(orderVo.getQuantity());
        orderBo.setPrice(orderVo.getPrice());

        return orderBo;
    }

}
