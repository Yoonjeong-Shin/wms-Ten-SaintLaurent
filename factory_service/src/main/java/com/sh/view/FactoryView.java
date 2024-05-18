package com.sh.view;

import com.sh.controller.FactoryController;
import com.sh.model.dao.FactoryMapper;
import com.sh.model.dto.FactoryDto;
import com.sh.model.dto.FactoryDto2;


import java.util.List;
import java.util.Scanner;

public class FactoryView {
    private FactoryController factoryController = new FactoryController();
    private Scanner sc = new Scanner(System.in);

    public static void displayOrderList(List<FactoryDto> list) {
        for (FactoryDto order : list) {
            System.out.println("Item ID: " + order.getItemId());
            System.out.println("Seller Name: " + order.getSellerNm());
            System.out.println("Item Category: " + order.getItemCat());
            System.out.println("Item Name: " + order.getItemNm());
            System.out.println("Item Volume: " + order.getItemVol());
            System.out.println("Expiration Date: " + order.getExpirationDate());
            System.out.println("Item Price: " + order.getItemPrice());
            System.out.println("Production Count: " + order.getProdCnt());
            System.out.println("Field: " + order.getField());
            System.out.println("Item Details:");
            for (FactoryDto2 detail : order.getFactoryDto2()) {
                System.out.println("  Serial Number: " + detail.getSerialNum());
                System.out.println("  Item State: " + detail.getItemState());
                System.out.println("  Item ID: " + detail.getItemId());
            }
            System.out.println("--------------------------------------");
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.println("1. Search Factory");
            System.out.println("0. Exit");
            System.out.print("번호를 입력하세요 : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    factoryController.searchFactory();
                    break;
                case 0:
                    System.out.println("나가는 중...");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }


}
