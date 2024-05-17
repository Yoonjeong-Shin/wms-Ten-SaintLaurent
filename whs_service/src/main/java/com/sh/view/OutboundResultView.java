package com.sh.view;

import com.sh.model.dto.OrderDto;
import com.sh.model.dto.PurchaseListDto;

import java.util.List;

public class OutboundResultView {

    public static void displayOrderList(List<OrderDto> list) {
        if (list.isEmpty()) {
            System.out.println("> 😅😅😅 조회된 출고정보가 없습니다. 😅😅😅");
        } else {
            System.out.println("-----------------------------------------------");
            System.out.printf("%s\t%s\t%s\n", "CustomerName", "CustomerAddress", "orderDetail");
            System.out.println("-----------------------------------------------");
            for (OrderDto orderDto : list) {
                System.out.printf("%s\t%s\t%s\n",
                        orderDto.getCustomerName(),
                        orderDto.getCustomerAdr(),
                        orderDto.getOrderDetailDtoList()
                );
            }
            System.out.println("-----------------------------------------------");
        }
    }


    public static void displayReportList(List<PurchaseListDto> list) {
        if (list.isEmpty()) {
            System.out.println("> 😅😅😅 조회된 출고정보가 없습니다. 😅😅😅");
        } else {
            System.out.println("-----------------------------------------------");
            System.out.printf("%s\t%s\t%s\n", "CustomerName", "CustomerAddress", "orderDetail");
            System.out.println("-----------------------------------------------");
            for (PurchaseListDto purchaseListDto : list) {
                System.out.printf("%s\t%s\t%s\n",
                        orderDto.getCustomerName(),
                        orderDto.getCustomerAdr(),
                        orderDto.getOrderDetailDtoList()
                );
            }
            System.out.println("-----------------------------------------------");
        }
    }
    public static void displayResult(String 카트_할당, int result) {
    }
}
