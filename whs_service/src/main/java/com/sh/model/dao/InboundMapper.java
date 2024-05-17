package com.sh.model.dao;

import com.sh.model.dto.InboundDto;

import java.util.List;

public interface InboundMapper {

    int approveInbound(InboundDto inboundDto); // 입고 승인

    int checkInbound(int inboundID); // 입고 검수
    void showInboundResult(InboundDto inboundDto); // 입고 확정
}
