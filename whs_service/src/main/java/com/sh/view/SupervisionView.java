package com.sh.view;

import com.sh.controller.SupervisionController;

import java.sql.SQLOutput;
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
            case "2" : break;
            // 폐기
            case "3" : break;
            default:
                System.out.println("잘못 선택하였습니다. 다시 선택해주세요.");
        }
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
//    private void searchLpn() {
//        superController.searchLpn();
//    }

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
