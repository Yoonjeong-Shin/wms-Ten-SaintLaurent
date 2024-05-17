package com.sh.controller;

import com.sh.model.dto.OrderDto;
import com.sh.model.dto.OutboundDto;
import com.sh.model.dto.PurchaseListDto;
import com.sh.model.service.OutboundService;
import com.sh.view.OutboundResultView;

import java.util.ArrayList;
import java.util.List;

public class OutboundController {
    private OutboundService outboundService = new OutboundService();

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

    public void assignCart(OutboundDto outboundDto){
        int result = outboundService.assignCart(outboundDto);
        OutboundResultView.displayResult("카트 할당", result);
    }

    public void OutboundPicking(){
        try {
            List<OutboundDto> list = outboundService.OutboundPicking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkOutbound(){
        try {
            boolean result = outboundService.checkOutbound();
            OutboundResultView.displayResult("출고 검수", result ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmOutbound(){
        try {
            boolean result = outboundService.confirmOutbound();
            OutboundResultView.displayResult("출고 확정", result ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectPicking(){
        try {
            boolean result = outboundService.rejectPicking();
            OutboundResultView.displayResult("픽킹 취소", result ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectCheck(){try {
        List<OutboundDto> list = outboundService.rejectcheck();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}
