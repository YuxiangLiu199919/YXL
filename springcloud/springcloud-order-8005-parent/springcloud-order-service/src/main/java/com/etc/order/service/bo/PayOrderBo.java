package com.etc.order.service.bo;

import lombok.Data;

@Data
public class PayOrderBo {
    /**
     * 买家ID
     */
    private String buyerId;

    /**
     * 卖家ID
     */
    private String sellerId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商品价格（总价）
     */
    private Double totalPrice;


}
