package com.etc.order.repository.dao;

import com.etc.order.repository.entity.OrderItem;

public interface OrderItemDao {

    /**
     * 创建订单明细
     * */
    Integer addOrderItem(OrderItem orderItem);
}
