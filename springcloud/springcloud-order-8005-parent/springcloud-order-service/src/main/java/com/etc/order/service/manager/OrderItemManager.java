package com.etc.order.service.manager;

import com.etc.order.repository.entity.OrderItem;

public interface OrderItemManager {
    /**
     * 创建订单明细
     * */
    Integer addOrderItem(OrderItem orderItem);
}
