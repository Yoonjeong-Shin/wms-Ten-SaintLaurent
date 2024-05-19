package com.sh.controller;

import com.sh.model.dto.*;
import com.sh.model.service.OutboundService;
import com.sh.view.OutboundResultView;


import java.util.List;

public class OutboundController {
    private OutboundService outboundService = new OutboundService();


    //== 출고 조회  -> 주문서 뽑아옴 ==//
    public void searchOutbound(){
        try {
            List<OrderDto> list = outboundService.searchOutbound();
            OutboundResultView.displayOrderList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printOutBoundReport(){
        try {
            List<PurchaseListDto> list = outboundService.printOutBoundReport();
            OutboundResultView.displayReportList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void assignCart(){
        List<OutBoundCartDto> outBoundCartDto = outboundService.assignCart();
        OutboundResultView.displayAssignCart(outBoundCartDto);
    }

    public void outboundPicking(){
        try {
            List<OutBoundPickDto> list = outboundService.outboundPicking();
            OutboundResultView.displayPickingCount(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkOutbound(OutboundCheckDto outboundCheckDto){
        try {
            int check = outboundService.checkOutbound(outboundCheckDto);
            OutboundResultView.displayCheckStatus(check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmOutbound(LocateDto locateDto){
        try {
            int update = outboundService.confirmOutbound(locateDto);
            OutboundResultView.displayCheckStatus(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
