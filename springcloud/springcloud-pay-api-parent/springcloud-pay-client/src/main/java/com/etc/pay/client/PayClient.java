package com.etc.pay.client;

import com.etc.pay.api.facade.IPayFacade;
import com.etc.pay.client.fallbackImpl.PayFallBack;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="springclond-pay",contextId="springclond-pay",url="www.etc:1000",
        fallback = PayFallBack.class)
public interface PayClient extends IPayFacade {
}
