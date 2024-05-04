package com.etc.order.service.strategy.impl;

import com.etc.order.repository.dao.OrderDao;
import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.ReturnResultBo;
import com.etc.order.service.emuns.MasterOrderEmuns;
import com.etc.order.service.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("payFailStrategy")
public class PayFailStrategy implements PayStrategy {

    @Autowired
    private OrderDao orderDao;

    @Override
    public ReturnResultBo payResult(PayOrderBo payOrderBo) {
        String statusCd= MasterOrderEmuns.ORDER_STATUS_PUB2.getValue();
        int flag=orderDao.updateMasterOrder(statusCd,payOrderBo.getOrderId());
        if(flag==0){
            return ReturnResultBo.success("数据库更新状态异常",payOrderBo);
        }
        return ReturnResultBo.success("支付失败",payOrderBo);
    }
}
