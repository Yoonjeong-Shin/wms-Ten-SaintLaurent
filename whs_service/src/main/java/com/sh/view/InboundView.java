package com.sh.view;

import com.sh.controller.InboundController;
import com.sh.common.InboundRepository;
import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import com.sh.model.dto.json.InbJsonDto;
import com.sh.model.dto.json.SelInboundOrder;
import com.sh.whsApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sh.whsApp.whsNM;
import static com.sh.whsApp.whsPk;

public class InboundView {
    public static void main(String[] args) {
        InboundView inboundView = new InboundView();
        SelInboundOrder order1 = new SelInboundOrder();
        order1.setId(1L);
        order1.setSellerName("Seller A");
        order1.setFactoryName("Factory A");
        order1.setCategory("스킨케어");
        order1.setItemName("제품3");
        order1.setVolume(10);
        order1.setExpirationDate(LocalDate.of(2024, 12, 31));
        order1.setPrice(100);
        order1.setProductCount(50);

        SelInboundOrder order2 = new SelInboundOrder();
        order2.setId(2L);
        order2.setSellerName("Seller A");
        order2.setFactoryName("Factory A");
        order2.setCategory("스킨케어");
        order2.setItemName("제품2");
        order2.setVolume(20);
        order2.setExpirationDate(LocalDate.of(2025, 1, 15));
        order2.setPrice(200);
        order2.setProductCount(30);

        SelInboundOrder order3 = new SelInboundOrder();
        order3.setId(3L);
        order3.setSellerName("Seller A");
        order3.setFactoryName("Factory A");
        order3.setCategory("스킨케어");
        order3.setItemName("제품1");
        order3.setVolume(15);
        order3.setExpirationDate(LocalDate.of(2024, 11, 20));
        order3.setPrice(150);
        order3.setProductCount(20);

        // SelInboundOrder 객체를 리스트에 추가
        List<SelInboundOrder> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
//        inboundView.inputInb(orderList);
//        inboundView.inputInb()
    }
    private Scanner sc = new Scanner(System.in);
    private InboundController inboundController = new InboundController();

//    public void inboundMenu() {
//        String menu = """
//        =========================
//        1. 입고 정보 조회
//        2. 창고 빈공간 조회
//        3. 입고 정보 추가
//        4. 추가한 폐기 상품 조회
//        0. 종료
//        =========================
//        입력 : """;
//        System.out.print(menu);
//        String choice = sc.next();
//        switch (choice) {
//            case "1" : inboundController.findByInbId(inputInbId()); break;
//            case "2" : inboundController.findEmptyLocate(); break;
//            case "3" : inboundController.insertInbToINB(inputInb()); break;
//            case "4" : inboundController.insertInbToGbgDetail(inputGbgDetail()); break;
////            case "5" : inboundController.updateInbCnt(inputInbCntUpdated()); break;
//            case "0" : return;
//            default:
//                System.out.println("잘못 입력하셨습니다.");
//        }
//    }

    // 입고 승인 - JSON에서 읽어와 Inb_TB에 넣기
    // 쿼리에 전달하는 객체는 무조건 1개여야 하는데 여러 객체를 한 번에 테이블에 어떻게 넣어야 할지 힘들다.


    // 입고 정보 조회
    // INB_TB의 PK로 한 데이터의 모든 정보를 조회, 입고 승인과 입고 확정 때 쓰인다
    public int inputInbId() {
        System.out.print("> 조회할 입고ID : ");
        return sc.nextInt();
    }

    // 입고 검수 - state가 2,3인 불량품은 gbg_detail_tb에 insert
    // 쿼리에 전달하는 객체는 무조건 1개여야 하는데 여러 객체를 한 번에 테이블에 어떻게 넣어야 할지 힘들다.
    public  GbgDetailDto inputGbgDetail(List<InbJsonDto> inbJsonDtos) {
        GbgDetailDto gbgDetailDto = new GbgDetailDto();

        // json에서 itemsDetail의 state가 2,3인 불량품 정보만 읽어오기 if(state != 1)
        for (int i=0; i<inbJsonDtos.size(); i++) {
            for(int j=0; j<inbJsonDtos.get(i).getItemsDetail().size(); j++) {
                if (inbJsonDtos.get(i).getItemsDetail().get(j).getState() != 1) {
                    gbgDetailDto = new GbgDetailDto(inbJsonDtos.get(i).getItemsDetail().get(j).getState(), inbJsonDtos.get(i).getItemsDetail().get(j).getItemSerialNum());
//                    InboundResultView.displayDefective(gbgDetailDto);
                    // insert를 여러 번 하기 위해 서비스를 여러번 호출하자
                    inboundController.insertInbToGbgDetail(gbgDetailDto);
                }
            }
        }
        return gbgDetailDto;
    }

    // 입고 검수 - INB_TB에서 불량 제품을 뺀 수량 update
//    private InboundDto inputInbCntUpdated() {
//        // json에서 itemsDetail의 state가 2,3인 불량품
//
//        // state가 2,3인 inbItemDto json에 있는 데이터와 같은 INB_TB 데이터에서 INB_ITEM_CNT-- 하기
//
//        return new InboundDto();
//    }
}
