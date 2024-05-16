package com.sh.view;

import com.sh.controller.SupervisionController;

import java.sql.SQLOutput;
import java.util.Scanner;

public class SupervisionView {
    private SupervisionController superController = new SupervisionController();

    Scanner sc = new Scanner(System.in);

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
            case "1" : inputCase(); break;
            case "2" : break;
            case "3" : break;
            default:
                System.out.println("잘못 선택하였습니다. 다시 선택해주세요.");
        }
    }

    // 상품관리 > 조회
    private void inputCase() {
        System.out.println("""
        ✨ 무엇을 조회하실건가요? ✨
        1. 화장품 정보
        2. 로케이션 정보
        : """);
        String num = sc.next();
        switch (num) {
            case "1" :
//                System.out.println("""
//                ✨ 무엇을 조회하실건가요? ✨
//                1. 화장품 입고 정보(이름 조회해서 그 이름의 화장품이 있는지 체크, 적재위치도 보여줌)
//                2. 화장품 수량 조회(이름 조회해서 해당 화장품 재고 얼마나 있나 조회)
//                : """);
//                searchItem(num);
                break;
            case "2" :
                searchLpn();
                break;
            default:
                System.out.println("잘못 입력했습니다. (inputCase)");
        }
    }

    // 로케이션 비어있는 공간 조회
    private void searchLpn() {
        superController.searchLpn();
    }

//    private void searchItem(String num) {
//        System.out.println("화장품 이름을 입력하세요.");
//        switch (num) {
//            case "1" : superController.searchItemInfo(sc.next()); break;
//            case "2" : superController.searchItemInfo(sc.next()); break;
//            default:
//                System.out.println("잘못 입력 (searchItem)");
//        }
//    }
}
