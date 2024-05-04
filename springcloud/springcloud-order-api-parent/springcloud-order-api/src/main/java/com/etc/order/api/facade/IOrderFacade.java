package com.etc.order.api.facade;

import com.etc.order.api.Result.ReturnResult;
import com.etc.order.api.dto.MasterOrderDTO;
import com.etc.order.api.dto.OrderDTO;
import com.etc.order.api.dto.PayOrderDTO;
import com.etc.order.api.dto.PlaseOrderDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 刘宇祥
 * 订单接入层
 * */
@Api("订单")
@RequestMapping("/order")
public interface IOrderFacade {

    /**
     * skuId查询订单列表
     * @RequestParam skuId
     * */
    @GetMapping(value = "/queryOrderBySku")
    PageInfo<OrderDTO> queryOrderBySku(@RequestParam(value = "skuId",required = false) String skuId,
                                       @RequestParam(value = "buyerId",required = false) String buyerId,
                                       @RequestParam(value = "pageNo",defaultValue = "1",required = false) Integer pageNo,
                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize) throws Exception;

    /**
     * 下单
     * @RequestBody PlaseOrderDTO
     * */
    @PostMapping(value = "/placeOrder")
    ReturnResult placeOrder(@RequestBody PlaseOrderDTO plaseOrderDTO) throws Exception;

    /**
     * 支付
     * @RequestBody PayOrderDTO
     * */
    @PostMapping(value = "/payOrder")
    ReturnResult payOrder(@RequestBody PayOrderDTO payOrderDTO) throws Exception;

}
