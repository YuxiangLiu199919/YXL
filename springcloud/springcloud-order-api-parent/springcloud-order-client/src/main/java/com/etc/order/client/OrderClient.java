package com.etc.order.client;

import com.etc.order.api.facade.IOrderFacade;
import com.etc.order.client.fallbackImpl.OrderFallBack;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="springclond-order-app",contextId="OrderClient",url="www.etc:8005",fallback = OrderFallBack.class)
public interface OrderClient extends IOrderFacade {
}
