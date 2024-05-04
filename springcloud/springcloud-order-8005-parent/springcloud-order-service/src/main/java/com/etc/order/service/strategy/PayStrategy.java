package com.etc.order.service.strategy;

import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.ReturnResultBo;


/**
 * @author 刘宇祥
 * */
public interface PayStrategy {

    ReturnResultBo payResult(PayOrderBo payOrderBo);


}
