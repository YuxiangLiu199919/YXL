package com.etc.order.client.fallbackImpl;

import com.etc.order.api.Result.ReturnResult;
import com.etc.order.api.dto.MasterOrderDTO;
import com.etc.order.api.dto.OrderDTO;
import com.etc.order.api.dto.PayOrderDTO;
import com.etc.order.api.dto.PlaseOrderDTO;
import com.etc.order.client.OrderClient;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFallBack implements OrderClient {

    @Override
    public PageInfo<OrderDTO> queryOrderBySku(String skuId,String buyerId, Integer pageNo, Integer pageSize) throws Exception {
        throw new Exception("queryOrderBySku方法异常");
    }

    @Override
    public ReturnResult placeOrder(PlaseOrderDTO plaseOrderDTO) throws Exception {
        throw new Exception("placeOrder方法异常");
    }

    @Override
    public ReturnResult payOrder(PayOrderDTO payOrderDTO) throws Exception {
        throw new Exception("payOrder方法异常");
    }
}
