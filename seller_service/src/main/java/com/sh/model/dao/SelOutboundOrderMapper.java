package com.sh.model.dao;

import com.sh.model.SelOutboundOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SelOutboundOrderMapper {


    // 모든 출고 주문 조회
    List<SelOutboundOrder> findAllOutboundOrders();
   }
