package com.etc.order.service.emuns;

/**
 * 订单表状态枚举
 * @author Yuxiang Liu
 * */
public enum MasterOrderEmuns {

    ORDER_STATUS_PUB1("ORDER_STATUS_PUB1","PUB001","待支付"),
    ORDER_STATUS_PUB2("ORDER_STATUS_PUB2","PUB002","支付成功，订单完结"),
    ORDER_STATUS_PUB3("ORDER_STATUS_PUB3","PUB003","支付失败"),
    ;

    private String key;

    private String value;

    private String desc;

    MasterOrderEmuns(String key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
