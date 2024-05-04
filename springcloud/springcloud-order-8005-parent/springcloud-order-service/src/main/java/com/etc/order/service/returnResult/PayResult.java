package com.etc.order.service.returnResult;


/**
 * 下单成功返回体
 * @author Yuxiang Liu
 * */
public class PayResult {

    /**
     * 订单ID
     * */
    private String orderId;

    /**
     * 买家ID
     * */
    private String buyerId;


    /**
     * 卖家ID
     */
    private String sellerId;

    /**
     * 总价
     * */
    private Double totalPrice;

    public PayResult() {
    }

    public PayResult(String orderId, String buyerId, String sellerId, Double totalPrice) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
