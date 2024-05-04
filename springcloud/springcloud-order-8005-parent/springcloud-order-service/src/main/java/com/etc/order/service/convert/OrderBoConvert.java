package com.etc.order.service.convert;

import com.etc.order.repository.vo.OrderVo;
import com.etc.order.service.bo.OrderBo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface OrderBoConvert {


    PageInfo<OrderBo> toPageInfoOrderBo(PageInfo<OrderVo> pageInfo);

    List<OrderBo> toOrderBoList(List<OrderVo> orderVoList);


}
