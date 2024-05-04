package com.etc.order.service.listener.event;

import com.etc.order.service.bo.PayOrderBo;


/**
 * @author 刘宇祥
 * 支付结果监听事件对象
 * */
public class PayResultEvent {

    private Boolean flag;

    private PayOrderBo payOrderBo;

    public PayResultEvent() {
    }

    public PayResultEvent(Boolean flag, PayOrderBo payOrderBo) {
        this.flag = flag;
        this.payOrderBo = payOrderBo;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public PayOrderBo getPayOrderBo() {
        return payOrderBo;
    }

    public void setPayOrderBo(PayOrderBo payOrderBo) {
        this.payOrderBo = payOrderBo;
    }
}
