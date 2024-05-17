package com.sh.model.dao;

import com.sh.model.dto.InboundDto;

public interface InboundMapper {

    int approveInbound(InboundDto inboundDto); // 입고 승인

    // 입고 검수
    int insertInboundToGBG(InboundDto inboundDto); // GBG_TB에 state가 2,3인 불량 제품을 insert
    int updateInboundCnt(InboundDto inboundDto); // INB_TB에서 불량 제품을 뺀 수량 update

    void showInboundResult(InboundDto inboundDto); // 입고 확정
}
