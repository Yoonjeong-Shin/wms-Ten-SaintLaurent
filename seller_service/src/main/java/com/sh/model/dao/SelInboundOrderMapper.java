package com.sh.model.dao;

import com.sh.model.SelInboundOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SelInboundOrderMapper {

    // 모든 입고 주문 조회
    List<SelInboundOrder> findAllInboundOrders();

    }
