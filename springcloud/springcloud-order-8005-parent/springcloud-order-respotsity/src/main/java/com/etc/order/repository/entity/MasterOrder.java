package com.etc.order.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MasterOrder implements Serializable {
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
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
