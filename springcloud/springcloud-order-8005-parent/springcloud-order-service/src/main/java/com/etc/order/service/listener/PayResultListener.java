package com.etc.order.service.listener;


import com.etc.order.service.bo.ReturnResultBo;
import com.etc.order.service.constants.Constant;
import com.etc.order.service.factory.PayResultfactory;
import com.etc.order.service.listener.event.PayResultEvent;
import com.etc.order.service.strategy.PayContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Optional;

@Component
public class PayResultListener {

    @Autowired
    private PayContext payContext;

    /**
     * 在主线程事务提交后执行以下逻辑，避免支付阻塞
     * */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async(Constant.SYNC_EXECUTOR)
    public void doPayResult(PayResultEvent payResultEvent){
        if(Boolean.FALSE.equals(verify(payResultEvent))){
            return;
        }
        try {
            //策略模式
            ReturnResultBo returnResultBo=payContext.executePayment(payResultEvent.getPayOrderBo(),payResultEvent.getFlag());
            //简单工厂模式
            //PayResultfactory.doPayResult(payResultEvent.getPayOrderBo(),payResultEvent.getFlag());
            if(StringUtils.equals("900",returnResultBo.getCode())){
                System.out.println("订单状态更新失败，记录日志");
            }
        }catch (Exception e){
            System.out.println("订单状态更新失败，记录日志");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * 校验监听参数
     * */
    private Boolean verify(PayResultEvent payResultEvent){
        if(!Optional.ofNullable(payResultEvent).isPresent()){
            return false;
        }
        if(!Optional.ofNullable(payResultEvent.getPayOrderBo()).isPresent()){
            return false;
        }
        if(!Optional.ofNullable(payResultEvent.getFlag()).isPresent()){
            return false;
        }
        return true;
    }
}
