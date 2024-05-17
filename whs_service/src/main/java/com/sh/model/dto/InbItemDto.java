package com.sh.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class InbItemDto {
    private String sellerName;
    private String factoryName;
    private String cat;
    private String itemName;
    private int vol;
    private int price;
    private int itemCount;
    private Date expirationDate;
    private List<InbItemDetailDto> itemsDetail;

    // 기본 생성자
    public InbItemDto() {
    }

    // 매개변수가 있는 생성자
    public InbItemDto(String sellerName, String factoryName, String cat, String itemName, int vol, int price, int itemCount, Date expirationDate, List<InbItemDetailDto> itemsDetail) {
        this.sellerName = sellerName;
        this.factoryName = factoryName;
        this.cat = cat;
        this.itemName = itemName;
        this.vol = vol;
        this.price = price;
        this.itemCount = itemCount;
        this.expirationDate = expirationDate;
        this.itemsDetail = itemsDetail;
    }
}