package com.sh.view;

import com.sh.controller.InboundController;
import com.sh.model.dto.InboundDto;

import java.math.BigInteger;
import java.util.Scanner;

public class InboundView {

    private InboundController inboundController = new InboundController();
    private Scanner sc = new Scanner(System.in);

    public void inboundMenu() {
//        String menu = """
//        =========================
//        1. 입고 관리
//        2. 창고 빈공간 조회
//        0. 종료
//        =========================
//        입력 : """;
        String choice = sc.next();
        switch (choice) {
//            case "1" : inboundController.findByInboundID(); break;
//            case "2" : inboundController.findEmptyLocate(); break;
//            case "3" : inboundController.insertInboundToINB(); break;
//            case "4" : inboundController.updateInboundCnt(); break;
//            case "5" : inboundController.insertInboundToGBG(inputGBG()); break;
            case "0" : return;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }

    // 입고 승인
    // JSON에서 읽어와 INB_TB에 넣기


    // 입고 검수
    // INB_TB에서 불량 제품을 GBG_TB에 넣기
    private InboundDto inputGBG() {
        // JSON 데이터에서 읽어오기
        return new InboundDto();
    }
}
