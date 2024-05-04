package com.etc.order.api.dto;

import lombok.Data;

@Data
public class PayOrderDTO {
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
