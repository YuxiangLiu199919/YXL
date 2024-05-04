package com.etc.order.api.dto;

import lombok.Data;
import java.util.Date;

@Data
public class MasterOrderDTO {
    /**
     * UID
     */
    private Long uid;
    /**
     * 订单ID
     */
    private String orderId;

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
     * 订单状态
     */
    private String orderStatus;
    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;



}
