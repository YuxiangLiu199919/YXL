package com.etc.order.repository.mapper;

import com.etc.order.repository.entity.MasterOrder;
import com.etc.order.repository.vo.OrderVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderVo> queryOrderBySku(@Param("skuId") String skuId,@Param("buyerId") String buyerId);

    Integer createOrder(MasterOrder masterOrder);

    Integer updateMasterOrder(@Param("statusCd") String statusCd,@Param("orderId") String orderId);

    MasterOrder queryByOrderId(@Param("orderId") String orderId,
                               @Param("buyerId") String buyerId,
                               @Param("orderStatus") String orderStatus);
}
