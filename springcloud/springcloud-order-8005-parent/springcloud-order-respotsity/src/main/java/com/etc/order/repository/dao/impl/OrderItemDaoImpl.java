package com.etc.order.repository.dao.impl;


import com.etc.order.repository.dao.OrderItemDao;
import com.etc.order.repository.entity.OrderItem;
import com.etc.order.repository.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Integer addOrderItem(OrderItem orderItem) {
        return orderItemMapper.addOrderItem(orderItem);
    }
}
