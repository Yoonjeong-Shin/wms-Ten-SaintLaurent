//package com.sh.view;
//
//import com.sh.model.dto.*;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//public class OutboundResultView {
//
//    // 출고 정보 출력
//    public static void displayOrderList(List<OrderDto> list) {
//        if (list.isEmpty()) {
//            System.out.println("> 😅😅😅 조회된 출고정보가 없습니다. 😅😅😅");
//        } else {
//            System.out.println("-------------------------------------------------------------------");
//            System.out.printf("%-15s %-20s %-30s\n", "CustomerName", "CustomerAddress", "OrderDetails");
//            System.out.println("-------------------------------------------------------------------");
//            for (OrderDto orderDto : list) {
//                System.out.printf("%s\t%s\n",
//                        orderDto.getCustomerName(),
//                        orderDto.getCustomerAdr()
//                );
//                System.out.println("Order Details:");
//                for (OrderDetailDto detail : orderDto.getOrderDetailDtoList()) {
//                    System.out.println(detail.getProductName());
//                    System.out.println(detail.getProductVolume());
//                    System.out.println(detail.getProductCount());
//                }
//                System.out.println("-------------------------------------------------------------------");
//            }
//        }
//    }
//
//    // 출고지시서 출력
//    public static void displayReportList(List<PurchaseListDto> list) {
//        if (list.isEmpty()) {
//            System.out.println("> 😅😅😅 작성된 출고지시서가 없습니다. 😅😅😅");
//        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println("-----------------------------------------------");
//            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", "OrderId", "UserId", "ItemName", "PickCnt", "OutboundId", "OutboundDate");
//            System.out.println("-----------------------------------------------");
//            for (PurchaseListDto purchaseListDto : list) {
//                String formattedDate = dateFormat.format(purchaseListDto.getOutboundDate());
//                for (ItemDto itemDto : purchaseListDto.getItemDtoList()) {
//                    System.out.printf("%d\t%d\t%s\t%d\t%d\t%s\n",
//                            purchaseListDto.getOrderId(),
//                            purchaseListDto.getUserId(),
//                            itemDto.getItemNm(),
//                            purchaseListDto.getPickCnt(),
//                            purchaseListDto.getOutboundId(),
//                            formattedDate
//                    );
//                }
//            }
//            System.out.println("-----------------------------------------------");
//        }
//    }
//
//    //카트할당
//    public static void displayAssignCart(List<OutBoundCartDto> result) {
//        System.out.println(result);
//    }
//
//
//    public static void displayPickingCount(List<OutBoundPickDto> list) {
//        System.out.println(list);
//    }
//
//    public static void displayCheckStatus(int check) {
//        System.out.println(check);
//    }
//}
