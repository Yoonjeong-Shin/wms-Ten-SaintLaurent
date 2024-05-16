package com.sh.controller;

import com.sh.model.dto.InboundDto;
import com.sh.model.service.InboundService;

import java.util.List;

public class InboundController {
    private InboundService inboundService = new InboundService();

    // 입고 생성
    public void insertInbound(){
        try {
            List<InboundDto> inbounLlist = inboundService.insertInbound();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 입고 검수
    /*
    입고 승인을 받아야한다.
    유통업체에게 입고 화장품 정보를 받습니다.

     */
    public void checkItemDefault(){

    }

    // 입고 확정
    public void confirmInbound(){

    }

    // 입고 거부
    public void rejectInbound(){

    }
}
