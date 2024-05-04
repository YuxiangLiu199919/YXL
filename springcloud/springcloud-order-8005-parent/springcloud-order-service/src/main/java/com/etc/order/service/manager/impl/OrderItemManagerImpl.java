package com.etc.order.service.manager.impl;


import com.etc.order.repository.dao.OrderItemDao;
import com.etc.order.repository.entity.OrderItem;
import com.etc.order.service.manager.OrderItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 订单明细服务类
 * @author Yuxiang Liu
 * */
@Service
public class OrderItemManagerImpl implements OrderItemManager {

    @Autowired
    private OrderItemDao orderItemDao;
    @Override
    public Integer addOrderItem(OrderItem orderItem) {
        return orderItemDao.addOrderItem(orderItem);
    }
}
