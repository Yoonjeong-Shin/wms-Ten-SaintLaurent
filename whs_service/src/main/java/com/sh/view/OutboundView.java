package com.sh.view;

import com.sh.controller.OutboundController;
import com.sh.model.dto.*;

import java.util.Scanner;

public class OutboundView {

    public static void main(String[] args) {
        new OutboundView().mainMenu();
    }
    private OutboundController outboundController = new OutboundController();
    private Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        String menu = """
        
        =====================
        1. 출고 조회
        2. 출고지시서 작성
        3. 카트 할당
        4. 출고 목록 픽킹
        5. 출고 상품 검수
        6. 출고 확정(재고반영)
        0. 종료
        ===================== 
        입력 : """;
        while(true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" : outboundController.searchOutbound(); break;
                case "2" : outboundController.printOutBoundReport(); break;
                case "3" : outboundController.assignCart(); break;
                case "4" : outboundController.outboundPicking(); break;
                case "5" : outboundController.checkOutbound(checkStatus());break;
                case "6" : outboundController.confirmOutbound(updateItemCnt(new OutboundCheckDto()));break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    private OutboundCheckDto checkStatus() {
        // 실제 OutboundCheckDto를 생성하거나 반환하는 로직이 여기에 들어갑니다.
        // 여기서는 예시로 임시 객체를 생성합니다.
        OutboundCheckDto dto = new OutboundCheckDto();

        // 로직에 따라 반환할 값 결정
        if (dto.getItemStatus() == 0 || dto.getPickCnt() > dto.getItemCnt()) {
            System.out.println("거부");
        } else {
            System.out.println("승인");
        }
        return dto;
    }

    private LocateDto updateItemCnt(OutboundCheckDto dto) {
        LocateDto locateDto = new LocateDto();
        // checkStatus의 반환값에 따라 locateItemCnt 값을 줄입니다.
        if (dto.getItemStatus() != 0 && dto.getPickCnt() <= dto.getItemCnt()) {
            int updatedCount = locateDto.getLocateItemCnt() - dto.getPickCnt();
            locateDto.setLocateItemCnt(updatedCount);
        }

        System.out.println("업데이트 된 재고 수량 : " + locateDto.getLocateItemCnt());

        return locateDto;
    }
}

