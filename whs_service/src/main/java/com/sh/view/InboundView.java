package com.sh.view;

import com.sh.controller.InboundController;
import com.sh.model.dto.InboundDto;

import java.math.BigInteger;
import java.util.Scanner;

public class InboundView {

    private InboundController inboundController = new InboundController();
    private Scanner sc = new Scanner(System.in);

    public void inboundMenu() {
        String menu = """
        =========================
        1. 입고 정보 조회
        2. 창고 빈공간 조회
        3. 입고 정보 추가
        4. 폐기 상품 추가
        5. 폐기 수량 제외한 입고 정보 조회
        0. 종료
        =========================
        입력 : """;
        System.out.println(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" : inboundController.findByInbId(inputInbId()); break;
//            case "2" : inboundController.findEmptyLocate(); break;
//            case "3" : inboundController.insertInbToINB(); break;
//            case "4" : inboundController.insertInbToGbg(inputGbg()); break;
//            case "5" : inboundController.updateInbCnt(inputInbCntUpdated()); break;
            case "0" : return;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }

    // 입고 승인
    // JSON에서 읽어와 INB_TB에 넣기


    /* 지영 작업 시작 */

    // 입고 정보 조회
    // INB_TB의 한 데이터의 모든 정보를 INB_ID_PK로 조회 (입고 승인과 입고 확정 때 쓰인다)
    private int inputInbId() {
        System.out.print("> 조회할 입고ID : ");
        return sc.nextInt();
    }

    // 입고 검수
    // GBG_TB에 state가 2,3인 불량 제품을 insert
    private InboundDto inputGbg() {
        // InbIntoItemDetailDto.json에서 state 읽어오기

        return new InboundDto();
    }
    // GBG_DETAIL_TB에 state가 2,3인 불량 제품을 insert
    private InboundDto inputGbgDetail() {
        // InbIntoItemDetailDto.json에서 state 읽어오기

        return new InboundDto();
    }

    // 입고 검수
    // INB_TB에서 불량 제품을 뺀 수량 update
    private InboundDto inputInbCntUpdated() {
        // InbIntoItemDto.json에 있는 데이터에서 itemsDetail의 state 읽어오기

        // if(state != 1)인 것만 카운트하기 -> state가 2,3인 불량품

        // state가 2,3인 InbItemDto.json에 있는 데이터와 같은 INB_TB 데이터에서 INB_ITEM_CNT-- 하기

        return new InboundDto();
    }

    /* 지영 작업 끝 */
}
