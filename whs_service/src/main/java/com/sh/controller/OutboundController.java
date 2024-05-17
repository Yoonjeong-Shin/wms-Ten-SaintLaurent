//package com.sh.controller;
//
//import com.sh.model.dto.OrderDto;
//import com.sh.model.dto.OutboundDto;
//import com.sh.model.dto.PurchaseListDto;
//import com.sh.model.service.OutboundService;
//import com.sh.view.OutboundResultView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OutboundController {
//    private OutboundService outboundService = new OutboundService();
//
//    public void searchOutbound(){
//        try {
//            List<OrderDto> list = outboundService.searchOutbound();
//            OutboundResultView.displayOrderList(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public void printOutBoundReport(){
//        try {
//            List<PurchaseListDto> list = outboundService.printOutBoundReport();
//            OutboundResultView.displayReportList(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void assignCart(OutboundDto outboundDto){
//        int result = outboundService.assignCart(outboundDto);
//        OutboundResultView.displayResult("카트 할당", result);
//    }
//
//    public void OutboundPicking(){
//    }
//
//    public void checkOutbound(){
//    }
//
//    public void confirmOutbound(){
//    }
//
//    public void rejectPicking(){
//    }
//
//    public void rejectCheck(){
//    }
//}
