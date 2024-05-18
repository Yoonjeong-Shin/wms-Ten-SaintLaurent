package com.sh.controller;

import com.sh.model.dto.OrderDto;
import com.sh.model.dto.OutboundDto;
import com.sh.model.dto.PurchaseListDto;
import com.sh.model.service.OutboundService;
import com.sh.view.OutboundResultView;


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
        OutboundResultView.displayAssignCart(result);
    }

//    public void outboundPicking(){
//        try {
//            List<OutboundDto> list = outboundService.outboundPicking();
//            OutboundResultView.displayPickingCount(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void checkOutbound(OutboundDto outboundDto){
//        try {
//            int result = outboundService.checkOutbound(outboundDto);
//            OutboundResultView.displayResult("출고 검수", result ? 1 : 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void confirmOutbound(){
//        try {
//            int result = outboundService.confirmOutbound();
//            OutboundResultView.displayResult("출고 확정", result ? 1 : 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void updateOutCnt(OutboundDto outboundDto) {
        int result = outboundService.updateOutCnt(outboundDto);
        OutboundResultView.displayOutCntResult("출고 후 재고수량", result);
    }
}
