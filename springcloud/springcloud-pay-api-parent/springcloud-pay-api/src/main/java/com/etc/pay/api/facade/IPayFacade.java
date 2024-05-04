package com.etc.pay.api.facade;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 支付接口
 * @author 刘宇祥
 * */
@Api("支付")
@RequestMapping("/pay")
public interface IPayFacade {

    @PostMapping(value = "/pay")
    Boolean pay(@RequestParam("money") Double money,@RequestParam("orderId") String orderId) throws Exception;


}
