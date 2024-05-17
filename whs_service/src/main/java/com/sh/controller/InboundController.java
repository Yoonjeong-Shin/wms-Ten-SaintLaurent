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
    public void checkInbound(int inboundID){
        inboundService.checkInbound(inboundID);
    }

    // 입고 확정
    public void showInboundResult(InboundDto inboundDto){
        inboundService.showInboundResult(inboundDto);
    }
}
