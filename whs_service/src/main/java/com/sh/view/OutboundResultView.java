package com.sh.view;

import com.sh.model.dto.OrderDetailDto;
import com.sh.model.dto.OrderDto;
import com.sh.model.dto.PurchaseListDto;

import java.text.SimpleDateFormat;
import java.util.List;

public class OutboundResultView {

    // 출고 정보 출력
    public static void displayOrderList(List<OrderDto> list) {
        if (list.isEmpty()) {
            System.out.println("> 😅😅😅 조회된 출고정보가 없습니다. 😅😅😅");
        } else {
            System.out.println("-------------------------------------------------------------------");
            System.out.printf("%-15s %-20s %-30s\n", "CustomerName", "CustomerAddress", "OrderDetails");
            System.out.println("-------------------------------------------------------------------");
            for (OrderDto orderDto : list) {
                System.out.printf("%s\t%s\n",
                        orderDto.getCustomerName(),
                        orderDto.getCustomerAdr()
                );
                System.out.println("Order Details:");
                for (OrderDetailDto detail : orderDto.getOrderDetailDtoList()) {
                    System.out.printf("s\t%d\t%d\n",
                            detail.getItemName(),
                            detail.getItemVol(),
                            detail.getItemCnt()
                    );
                }
                System.out.println("-------------------------------------------------------------------");
            }
        }
    }

    // 출고지시서 출력
    public static void displayReportList(List<PurchaseListDto> list) {
        if (list.isEmpty()) {
            System.out.println("> 😅😅😅 작성된 출고지시서가 없습니다. 😅😅😅");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("-----------------------------------------------");
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", "OrderId", "UserId", "ItemName","PickCnt","OutboundId","OutboundDate");
            System.out.println("-----------------------------------------------");
            for (PurchaseListDto purchaseListDto: list) {
                String formattedDate = dateFormat.format(purchaseListDto.getOutboundDate());
                System.out.printf("%d\t%d\t%s\t%d\t%d\t%s\n",
                        purchaseListDto.getOrderId(),
                        purchaseListDto.getUserId(),
                        purchaseListDto.getItemDto(),
                        purchaseListDto.getPickCnt(),
                        purchaseListDto.getOutboundId(),
                        formattedDate
                );
            }
            System.out.println("-----------------------------------------------");
        }

    }

    //카트할당
    public static void displayAssignCart(int result) {
        System.out.println(result > 0 ? "카트할당" : "할당실패");
    }

    public static void displayOutCntResult(String type, int result) {
        System.out.println("> 📢📢📢 " + type + " " + (result > 0 ? "성공!" : "실패!") + "📢📢📢");
    }

    public static void displayPickingCount() {
        System.out.println("> 😅😅😅 작성된 출고지시서가 없습니다. 😅😅😅");
        System.out.printf("");
    }
}
