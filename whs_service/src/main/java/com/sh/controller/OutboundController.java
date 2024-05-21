package com.sh.controller;

import com.sh.model.dto.*;
import com.sh.model.dto.json.SelOutboundOrder;
import com.sh.model.service.OutboundService;
import com.sh.model.service.SupervisionService;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OutboundController {
    private OutboundService outboundService = new OutboundService();
    private SupervisionService supervisionService = new SupervisionService();


    public List<Boolean> outbLogic(List<SelOutboundOrder> selOutboundOrders){
       List<Boolean> outbLogicList = new ArrayList<>();
        for(SelOutboundOrder selOutboundOrder:selOutboundOrders){ // 재고 파악
            if(selOutboundOrder.getProductCount() > outboundService.checkItemCount(selOutboundOrder.getItemName())){
                outbLogicList.add(false);
            }
            else{
                outbLogicList.add(true);
                long outbPk = outboundService.createOutbTB(selOutboundOrder.getCusNM()); // 출고서
//                System.out.println("Con createOutbTB" + outbPk);
                System.out.println("출고서 상세 정보 생성");
                long outbDetailPk = outboundService.createOutbDetailTB(outbPk,selOutboundOrder.getItemName(),selOutboundOrder.getProductCount()); // 출고서 상세
                System.out.println(outbDetailPk);
                long outbCartPk = outboundService.createOutbCartTB(outbDetailPk,selOutboundOrder.getProductCount()); // 카트 할당
                System.out.println("카트 할당중");

//                System.out.println(outbDetailPk);
                System.out.println("출고 화장품 상세 생성");
                outboundService.createOutBItemDetailTB(selOutboundOrder.getItemName(),selOutboundOrder.getProductCount(),selOutboundOrder.getProductCount());
                long itemId = supervisionService.searchItemId(selOutboundOrder.getItemName());
//                System.out.println(itemId);
                System.out.println("재고 수량 감소");
                supervisionService.updareItemCnt(selOutboundOrder.getProductCount() * -1,itemId);
                for(int i = 0;i<selOutboundOrder.getProductCount();i++){
                    long itemDetailPk = outboundService.selectForDeleteItemDetail(selOutboundOrder.getItemName());
                    System.out.println(itemDetailPk+"```");
                    LocateDto lpn = supervisionService.searchItemDetailLpn(itemDetailPk);
                    supervisionService.updareLocateCnt(-1,lpn.getLocateLpnCode());
                    outboundService.deleteOutBItemDetailTB(itemDetailPk);
                }


            }

        }
        return outbLogicList;
    }

}
