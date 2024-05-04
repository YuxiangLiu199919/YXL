package com.etc.pay.client.fallbackImpl;

import com.etc.pay.client.PayClient;
import org.springframework.stereotype.Service;

@Service
public class PayFallBack implements PayClient {

    @Override
    public Boolean pay(Double money,String orderId) throws Exception {
        throw new Exception("pay方法异常");
    }
}
