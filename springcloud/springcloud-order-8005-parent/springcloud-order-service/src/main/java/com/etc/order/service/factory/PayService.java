package com.etc.order.service.factory;

import com.etc.order.service.bo.PayOrderBo;
import com.etc.order.service.bo.ReturnResultBo;

/**
 * @author Yuxiang Liu
 * 支付策略上下文
 * */
public interface PayService {


    ReturnResultBo doAction(PayOrderBo payOrderBo);


    boolean isAction(Boolean flag);
}
