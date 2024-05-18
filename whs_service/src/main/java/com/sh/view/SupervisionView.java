package com.sh.view;

import com.sh.common.InboundRepository;
import com.sh.controller.SupervisionController;
import com.sh.model.dto.ItemCatDto;
import com.sh.model.dto.json.InbJsonDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupervisionView {

    public static void main(String[] args) {
        new SupervisionView().itemMenu();
//        System.out.println(list);
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
            case "2" : insertItem(); break;
            // 폐기
            case "3" : deleteItem(); break;
            default:
                System.out.println("잘못 선택하였습니다. 다시 선택해주세요.");
        }
    }

    private void insertItem() {
        // 화장품 품목 테이블 저장
        // json에서 가져온 화장품 품목명 일치 체크하면서 저장
//        System.out.println("View");
        List<ItemCatDto> itemCatList = superController.searchItemCat();
        System.out.println(itemCatList);

        if(itemCatList != null) {
            // 화장품 품목이 없는 경우
            superController.insertCatItem(itemCatList);

            // 화장품 테이블 저장
            superController.insertItem();
            // 화장품 디테일 테이블 저장
            // 화장품 적재 시 같은 제품이 적재된 곳이 있는가 조회
            superController.searchSameItemLpn();
            // 있다 > 그럼 존재하f는 적재 공간에 여유가 되는가
            // 된다 > 해당 공간 여유되는만큼만 적재 > 다음 로케이션에 적재
            // 안된다 > 다음 로케이션에 적재

            // 없다 > 빈 로케이션 조회
        }else {
            // 품목이 이미 있다면
            // item_tb에 적재 시작
            System.out.println("적재 시작");
            superController.insertItem();

            // 화장품 디테일 테이블 저장
            // 화장품 적재 시 같은 제품이 적재된 곳이 있는가 조회
            boolean bool = superController.searchSameItemLpn();

            if(bool) {
                // 있다 > 그럼 존재하는 적재 공간에 여유가 되는가
                // 된다 > 해당 공간 여유되는만큼만 적재 > 다음 로케이션에 적재
                // 안된다 > 다음 로케이션에 적재
            }else {
                // 없다 > 빈 로케이션 조회
                boolean lpnCk = superController.searchLpn();
//                superController.insertItemDetail();
            }

        }




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
                superController.searchLpn();
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

    // 화장품 이름 정보 받음
//    private int searchItem() {
//        System.out.println("화장품 아이디를 입력하세요.");
////        showItemNm();
//        return sc.nextInt();
//    }
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
