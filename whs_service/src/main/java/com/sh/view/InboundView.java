package com.sh.view;

import com.sh.controller.InboundController;
import com.sh.model.dto.json.InbDetailJsonDto;
import com.sh.model.repository.InboundRepository;
import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import com.sh.model.dto.json.InbJsonDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InboundView {
    public static void main(String[] args) {
        new InboundView().inboundMenu();
    }
    private Scanner sc = new Scanner(System.in);
    private InboundController inboundController = new InboundController();
    private InboundRepository inboundRepository = new InboundRepository();
    private List<InbJsonDto> inbJsonList = new ArrayList<>(inboundRepository.readInb());

    public void inboundMenu() {
        String menu = """
        =========================
        1. 입고 정보 조회
        2. 창고 빈공간 조회
        3. 입고 정보 추가
        4. 추가한 폐기 상품 조회
        5. 폐기 수량 제외한 입고 정보 조회
        0. 종료
        =========================
        입력 : """;
        System.out.print(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" : inboundController.findByInbId(inputInbId()); break;
            case "2" : inboundController.findEmptyLocate(); break;
//            case "3" : inboundController.insertInbToINB(inputInb()); break;
//            case "4" : inboundController.insertInbToGbgDetail(inputGbgDetail()); break;
//            case "5" : inboundController.updateInbCnt(inputInbCntUpdated()); break;
            case "0" : return;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }

    /* 지영 작업 시작 */

    // 입고 승인 - JSON에서 읽어와 Inb_TB에 넣기
    // 쿼리에 전달하는 객체는 무조건 1개여야 하는데 여러 객체를 한 번에 테이블에 어떻게 넣어야 할지 힘들다.
    private InboundDto inputInb() {
        InboundDto inboundDto = new InboundDto();
        String inbItemNM;
        int inbItemVol;
        int inbItemPrice;
        int inbItemCnt;
        LocalDate inbItemExpirationDt;
        for (int i=0; i<inbJsonList.size(); i++) {
            inbItemNM = inbJsonList.get(i).getItemName();
            inbItemVol = inbJsonList.get(i).getVol();
            inbItemPrice = inbJsonList.get(i).getPrice();
            inbItemCnt = inbJsonList.get(i).getItemCount();
            inbItemExpirationDt = inbJsonList.get(i).getExpirationDate();
        }
        return null;
    }

    // 입고 정보 조회
    // INB_TB의 한 데이터의 모든 정보를 INB_ID_PK로 조회 (입고 승인과 입고 확정 때 쓰인다)
    private int inputInbId() {
        System.out.print("> 조회할 입고ID : ");
        return sc.nextInt();
    }

    // 입고 검수 - GBG_DETAIL_TB에 state가 2,3인 불량 제품을 insert
    // 쿼리에 전달하는 객체는 무조건 1개여야 하는데 여러 객체를 한 번에 테이블에 어떻게 넣어야 할지 힘들다.
    private GbgDetailDto inputGbgDetail() {
        GbgDetailDto gbgDetailDto = new GbgDetailDto();

        // json에서 itemsDetail의 state가 2,3인 불량품 정보만 읽어오기 if(state != 1)
        for (int i=0; i<inbJsonList.size(); i++) {
            for(int j=0; j<inbJsonList.get(i).getItemsDetail().size(); j++) {
                if (inbJsonList.get(i).getItemsDetail().get(j).getState() != 1) {
                    gbgDetailDto = new GbgDetailDto(inbJsonList.get(i).getItemsDetail().get(j).getState(), inbJsonList.get(i).getItemsDetail().get(j).getItemSerialNum());
                    InboundResultView.displayDefective(gbgDetailDto);
                }
            }
        }
        return gbgDetailDto;
    }

    // 입고 검수 - INB_TB에서 불량 제품을 뺀 수량 update
    private InboundDto inputInbCntUpdated() {
        // json에서 itemsDetail의 state가 2,3인 불량품

        // state가 2,3인 inbItemDto json에 있는 데이터와 같은 INB_TB 데이터에서 INB_ITEM_CNT-- 하기

        return new InboundDto();
    }

    /* 지영 작업 끝 */
}
