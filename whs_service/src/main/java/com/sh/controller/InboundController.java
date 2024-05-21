package com.sh.controller;

import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import com.sh.model.dto.json.SelInboundOrder;
import com.sh.model.service.InboundService;
//import com.sh.view.InboundResultView;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.List;

import static com.sh.whsApp.whsNM;
import static com.sh.whsApp.whsPk;

public class InboundController {
    private InboundService inboundService = new InboundService();

    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public void findEmptyLocate() {
        int result = inboundService.findEmptyLocate();
        System.out.println(result == 0 ? "빈 공간이 없습니다":"빈 공간이 있습니다");
        // locate_id받아와서 LPN위치까지 추가해줘야하남..
    }

    public InboundDto inputInb(List<SelInboundOrder> inbJsonDtos) {
        InboundDto inboundDto = new InboundDto();

        String inbSelName;
        String inbFactoryName;
        String inbWhsName;
        String inbCategory;
        String inbItemNM;
        int inbItemVol;
        int inbItemPrice;
        int inbItemCnt;

        LocalDate inbItemExpirationDt;
        for (int i=0; i<inbJsonDtos.size(); i++) {
            inbSelName = inbJsonDtos.get(i).getSellerName();
            inbFactoryName = inbJsonDtos.get(i).getFactoryName();

            inbCategory = inbJsonDtos.get(i).getCategory();

            inbItemNM = inbJsonDtos.get(i).getItemName();
            inbItemVol = inbJsonDtos.get(i).getVolume();
            inbItemPrice = inbJsonDtos.get(i).getPrice();
            inbItemCnt = inbJsonDtos.get(i).getProductCount();
            inbItemExpirationDt = inbJsonDtos.get(i).getExpirationDate();
            long whspk = whsPk;
            inboundDto = new InboundDto(inbSelName,inbFactoryName, whsNM,inbCategory,inbItemNM, inbItemVol, inbItemPrice, inbItemCnt, inbItemExpirationDt);
            insertInbToItemTb(inboundDto);
            insertInbToINB(inboundDto);
        }
        return inboundDto;
    }
    // JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
    public void insertInbToINB(InboundDto inboundDto) {
        inboundService.insertInbToINB(inboundDto);
    }

    // JSON에서 얻은 state가 1인 정상 제품은 ITEM_TB와 ITEM_DETAIL_TB에 insert
    public void insertInbToItemTb(InboundDto inboundDto) {
        System.out.println("---------------------------------");
        inboundService.insertItem(inboundDto);
    }
    public void insertInbToItemDetailTb(InboundDto inboundDto) {
        inboundService.insertInbToItemDetailTb(inboundDto);
    }

    // 입고 정보 조회
    // INB_TB의 PK로 한 데이터의 모든 정보를 조회, 입고 승인과 입고 확정 때 쓰인다
    public void findByInbId(int inbIdPk) {
        try {
            InboundDto inboundDto = inboundService.findByInbId(inbIdPk);
//            InboundResultView.displayInb(inboundDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 입고 검수
    // JSON에서 얻은 state가 2,3인 불량품은 GBG_DETAIL_TB에 insert
    public void insertInbToGbgDetail(GbgDetailDto gbgDetailDto){
        inboundService.insertInbToGbgDetail(gbgDetailDto);
    }
    // GBG_DETAIL_TB에 insert한 불량품은 ITEM_DETAIL_TB에서 delete
    public void deleteItemDetail() {
        inboundService.deleteItemDetail();
    }

    // 재고에 있는 상품이 또 들어오면 재고 수량 더하기 update
    public void updateCntWithSum(int inbItemPk){
        inboundService.updateCntWithSum(inbItemPk);
    }
    // ITEM_DETIAL_TB에서 state가 2,3인 불량품이 들어오면 재고 수량 빼기 update
    public void updateCntWithMinus(int itemDetailPk) {
        inboundService.updateCntWithMinus(itemDetailPk);
    }
}
