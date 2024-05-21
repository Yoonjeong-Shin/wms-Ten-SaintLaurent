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

    public static void main(String[] args) {
        OutboundController outboundController = new OutboundController();
        // Example 1
        List<SelOutboundOrder> selOutboundOrders = new ArrayList<>();

        SelOutboundOrder order1 = new SelOutboundOrder();
        order1.setId(1L);
        order1.setSellerName("Seller A");
        order1.setCategory("Category 1");
        order1.setItemName("Item1");
        order1.setVolume(10);
        order1.setProductCount(1);
        order1.setCusNM("김도현");
        selOutboundOrders.add(order1);
        System.out.println(outboundController.outbLogic(selOutboundOrders));
    }
    public boolean createOrder(){

        return false;
    }
    public List<Boolean> outbLogic(List<SelOutboundOrder> selOutboundOrders){
       List<Boolean> outbLogicList = new ArrayList<>();
        for(SelOutboundOrder selOutboundOrder:selOutboundOrders){
            if(selOutboundOrder.getProductCount() > outboundService.checkItemCount(selOutboundOrder.getItemName())){
                outbLogicList.add(false);
            }
            else{
                outbLogicList.add(true);
                long outbPk = outboundService.createOutbTB(selOutboundOrder.getCusNM());
                System.out.println(outbPk);
                long outbDetailPk = outboundService.createOutbDetailTB(outbPk,selOutboundOrder.getItemName(),selOutboundOrder.getProductCount());
                System.out.println(outbDetailPk);
                long outbCartPk = outboundService.createOutbCartTB(outbDetailPk,selOutboundOrder.getProductCount());
                System.out.println(outbDetailPk);
                outboundService.createOutBItemDetailTB(selOutboundOrder.getItemName(),selOutboundOrder.getProductCount(),selOutboundOrder.getProductCount());
                long itemId = supervisionService.searchItemId(selOutboundOrder.getItemName());
                System.out.println(itemId);
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
