package com.etc.order.service.bo;

import lombok.Data;

@Data
public class PlaseOrderBo {
    /**
     * 买家ID
     */
    private String buyerId;

    /**
     * 卖家ID
     */
    private String sellerId;
    /**
     * SKU ID
     */
    private String skuId;

    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品价格（单价）
     */
    private Double price;

    /**
     * 备注
     */
    private String remark;



}
