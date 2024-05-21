package com.sh.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDate;
@Getter
@Setter
public class SelOutboundOrder {
    private Long id; // SEL_OUTB_ORDER_PK
    private String sellerName; // SEL_OUTB_ORDER_SELLER_NM
    private String category; // SEL_OUTB_ORDER_CAT
    private String itemName; // SEL_OUTB_ORDER_ITEM_NM
    private int volume; // SEL_OUTB_ORDER_VOL
    private Integer productCount; // SEL_OUTB_ORDER_PROD_CNT, nullable
    private String cusNM;
    // Getters and setters

    @Override
    public String toString() {
        return "SelOutboundOrder{" +
                "id=" + id +
                ", sellerName='" + sellerName + '\'' +
                ", category='" + category + '\'' +
                ", itemName='" + itemName + '\'' +
                ", volume=" + volume +
                ", productCount=" + productCount +
                ", cusNM=" + cusNM +
                '}'+"\n";
    }
}
