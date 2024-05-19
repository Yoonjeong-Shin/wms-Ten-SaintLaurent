package com.sh.controller;

import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import com.sh.model.service.InboundService;
import com.sh.view.InboundResultView;
import org.w3c.dom.ls.LSOutput;

public class InboundController {
    private InboundService inboundService = new InboundService();

    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public void findEmptyLocate() {
        int result = inboundService.findEmptyLocate();
        System.out.println(result == 0 ? "빈 공간이 없습니다":"빈 공간이 있습니다");
        // locate_id받아와서 LPN위치까지 추가해줘야하남..
    }

    // JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
    public void insertInbToINB(InboundDto inboundDto) {
        inboundService.insertInbToINB(inboundDto);
    }

    // state가 1인 정상 제품은 item_tb와 item_detail_tb에 insert
    public void insertInbToItemTb(InboundDto inboundDto) {
        inboundService.insertInbToItemTb(inboundDto);
    }
    public void insertInbToItemDetailTb(InboundDto inboundDto) {
        inboundService.insertInbToItemDetailTb(inboundDto);
    }

    // 입고 정보 조회
    // INB_TB의 PK로 한 데이터의 모든 정보를 조회, 입고 승인과 입고 확정 때 쓰인다
    public void findByInbId(int inbIdPk) {
        try {
            InboundDto inboundDto = inboundService.findByInbId(inbIdPk);
            InboundResultView.displayInb(inboundDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 입고 검수
    // state가 2,3인 불량품은 gbg_detail_tb에 insert
    public void insertInbToGbgDetail(GbgDetailDto gbgDetailDto){
        inboundService.insertInbToGbgDetail(gbgDetailDto);
    }
    // gbg_detail_tb에 insert한 불량품은 item_detail_tb에서 delete
    public void deleteItemDetail() {
        inboundService.deleteItemDetail();
    }

//    public void updateInbCnt(InboundDto inboundDto){
//        inboundService.updateInbCnt(inboundDto);
//    }
}
