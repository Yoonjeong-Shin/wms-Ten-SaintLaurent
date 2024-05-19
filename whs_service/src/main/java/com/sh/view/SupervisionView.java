package com.sh.view;

import com.sh.controller.SupervisionController;
import com.sh.model.dto.ItemCatDto;
import com.sh.model.dto.LocateDto;
import com.sh.model.dto.json.InbJsonDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupervisionView {

    public static void main(String[] args) {
        new SupervisionView().itemMenu();
    }
    private SupervisionController superController = new SupervisionController();

    Scanner sc = new Scanner(System.in);

    // 화장품 관리 메뉴
    public void itemMenu() {
        System.out.print("""
        --------------------------------
        화장품 관리
        --------------------------------
        1. 조회
        2. 입고 화장품 관리
        3. 화장품 불량 관리
        : """);
        String choice = sc.next();
        switch (choice) {
            // 조회
            case "1" : inputCase(); break;
            // 상품 추가
//            case "2" : insertItem(); break;
            // 폐기
            case "3" : deleteItem(); break;
            default:
                System.out.println("잘못 선택하였습니다. 다시 선택해주세요.");
        }
    }

    private void insertItem(List<InbJsonDto> inbJsonDtoList) {
        // 화장품 품목 테이블 저장

//        int forCnt = inbJsonDtoList.size()/50;
//        List<List<InbJsonDto>> inbJsonDtoLists = new ArrayList<>();
//
//        for (int i = 0; i < forCnt; i++) {
//            inbJsonDtoLists = inbJsonDtoLists(inbJsonDtoList, 50);
//        }
//
//        for(inbJsonDtoList1(50) : inbJsonDtoLists(300)){
//            // json에서 가져온 화장품 품목명 일치 체크하면서 저장
////        superController.insertCatItem(inbJsonDtoList1(50)); // 있으면 암것도 안함, 없으면 넣음.
////        // item_tb에 적재 시작
////        superController.insertItem(inbJsonDtoList1);// 있으면 암것도 안함, 없으면 넣음
////        // 화장품 디테일 테이블 저장
////        // 화장품 적재 시 같은 제품이 적재된 곳이 있는가 조회
////        superController.insertDetailItem(inbJsonDtoList1);//
//
//        }
    }


    private void deleteItem() {
        superController.deleteItem();
    }

    // 상품관리 > 조회
    private void inputCase() {
        System.out.print("""
        ✨ 무엇을 조회하실건가요? ✨
        1. 화장품 전체 정보 조회
        2. 화장품 수량
        3. 화장품 위치
        4. 로케이션 공간 체크
        : """);
        String num = sc.next();
        switch (num) {
            case "1" :
                superController.searchItemInfo();
                break;
            case "2" :
                superController.searchItemCnt(searchItem());
                break;
            case "3" :
                superController.searchItemLpn(searchItem());
                break;
            case "4" :
                List<LocateDto> locateList = superController.searchLpn();
                if(!locateList.isEmpty()) {
                    System.out.println("searchLpn" + locateList);
                }else System.out.println("공간 없음");
                break;
            default:
                System.out.println("잘못 입력했습니다. (inputCase)");
        }
    }

    // 로케이션 비어있는 공간 조회
    private void searchLpn() {
        // 창고 번호 넘겨줘야함
        superController.searchLpn();
    }

    private int searchItem() {
        showItemNm();
        System.out.print("화장품 아이디를 입력하세요. : ");
        return sc.nextInt();
    }

    // 화장품 ID와 이름 보여주는 메소드
    private void showItemNm() {
        superController.searchItemIdNNm();
    }
}
