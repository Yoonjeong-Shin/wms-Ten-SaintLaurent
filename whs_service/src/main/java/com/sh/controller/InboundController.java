package com.sh.controller;

import com.sh.model.dto.InboundDto;
import com.sh.model.service.InboundService;

import java.util.List;

public class InboundController {
    private InboundService inboundService = new InboundService();

    // 입고 승인
    public void approveInbound(InboundDto inboundDto){
        inboundService.approveInbound(inboundDto);
    }

    // 입고 검수
    // GBG_TB에 state가 2,3인 불량 제품을 insert
    public void insertInboundToGBG(InboundDto inboundDto){
        inboundService.insertInboundToGBG(inboundDto);
    }

    // 입고 검수
    // INB_TB에서 불량 제품을 뺀 수량 update
    public void updateInboundCnt(InboundDto inboundDto){
        inboundService.updateInboundCnt(inboundDto);
    }

    // 입고 확정
    public void showInboundResult(InboundDto inboundDto){
        inboundService.showInboundResult(inboundDto);
    }
}
