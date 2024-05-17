package com.sh.controller;

import com.sh.model.dto.InboundDto;
import com.sh.model.service.InboundService;

import java.util.List;

public class InboundController {
    private InboundService inboundService = new InboundService();

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    // INB_TB의 한 데이터를 INB_ID_PK로 조회

    public void findByInboundID(int inboundID) {
        inboundService.findByInboundID(inboundID);
    }

    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public void findLocateItemCnt() {
//        inboundService.findLocateItemCnt();
    }

    // 입고 승인
    // INB_TB에 JSON 데이터 넣기
    public void insertInboundToINB(InboundDto inboundDto) {
        inboundService.insertInboundToINB(inboundDto);
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
}
