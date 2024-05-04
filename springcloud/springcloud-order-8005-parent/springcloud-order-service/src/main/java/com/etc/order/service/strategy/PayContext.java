package com.etc.order.service.strategy;


import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.ReturnResultBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Yuxiang Liu
 * 支付策略上下文
 * */

@Component
public class PayContext {

    @Autowired
    @Qualifier("paySuccessStrategy")
    private PayStrategy successfulPaymentStrategy;

    @Autowired
    @Qualifier("payFailStrategy")
    private PayStrategy failedPaymentStrategy;

    public ReturnResultBo executePayment(PayOrderBo payOrderBo, boolean isSuccess) {
        PayStrategy strategy = isSuccess ? successfulPaymentStrategy : failedPaymentStrategy;
        return strategy.payResult(payOrderBo);
    }



}
