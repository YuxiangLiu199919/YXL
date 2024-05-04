package com.etc.order.repository.dao;

import com.etc.order.repository.entity.MasterOrder;
import com.etc.order.repository.vo.OrderVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderDao {

    PageInfo<OrderVo> queryOrderBySku(String skuId,String buyerId, Integer pageNo, Integer pageSize);

    MasterOrder queryByOrderId(String orderId,String buyerId,String orderStatus);

    Integer createOrder(MasterOrder masterOrder);

    Integer updateMasterOrder(String statusCd,String orderId);
}
