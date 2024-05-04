package com.etc.order.repository.mapper;

import com.etc.order.repository.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {

    /**
     * 创建订单明细
     * */
    Integer addOrderItem(OrderItem orderItem);
}
