package com.etc.order.service.factory;

import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.ReturnResultBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 简单工厂
 * @author 刘宇祥
 * */
@Component
public class PayResultfactory {

    private static List<PayService> payServiceList;

    @Autowired
    public static void setPayServiceList(List<PayService> payServiceList) {
        PayResultfactory.payServiceList = payServiceList;
    }

    public static ReturnResultBo doPayResult(PayOrderBo payOrderBo, boolean isSuccess){
        for(PayService s : payServiceList){
            if(s.isAction(isSuccess)){
                return s.doAction(payOrderBo);
            }
        }
        return ReturnResultBo.success("无法更新订单状态",payOrderBo);
    }

}
