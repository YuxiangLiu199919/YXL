package com.etc.springcloud.controller;

import com.etc.pay.api.facade.IPayFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController implements IPayFacade {

    /**
     * 模拟支付
     * */
    @Override
    public Boolean pay(Double money,String orderId) {
        if(StringUtils.isNotBlank(orderId) && money != null ){
            return true;
        }
        //模拟支付返回
        return false;
    }

}
