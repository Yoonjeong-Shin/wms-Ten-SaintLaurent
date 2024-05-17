//package com.sh.view;
//
//import com.sh.controller.OutboundController;
//import com.sh.model.dto.OutboundDto;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class OutboundView {
//    private OutboundController outboundController = new OutboundController();
//    private Scanner sc = new Scanner(System.in);
//
//    public void mainMenu() {
//        String menu = """
//        =====================
//        1. 출고 조회
//        2. 출고지시서 작성
//        3. 카트 할당
//        4. 출고 목록 픽킹
//        5. 출고 상품 검수
//        0. 종료
//        =====================
//        입력 : """;
//        while(true) {
//            System.out.print(menu);
//            String choice = sc.next();
//            switch (choice) {
//                case "1" : outboundController.searchOutbound(); break;
//                case "2" : outboundController.printOutBoundReport(); break;
//                case "3" : outboundController.assignCart(inputCart()); break;
//                case "4" : outboundController.OutboundPicking(); break;
//                case "5" : outboundController.checkOutbound();break;
//                case "0" : return;
//                default:
//                    System.out.println("잘못 입력하셨습니다.");
//            }
//        }
//    }
//
//    private OutboundDto inputCart() {
//        System.out.println("> ✏✏✏ 출고서 정보에 따라 카트를 할당합니다. ✏✏✏");
//        return null;
//    }
//}
