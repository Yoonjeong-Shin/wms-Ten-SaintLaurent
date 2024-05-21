package com.sh.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor

public class InboundDto {
    private String selNm; // 유통업체 nm
    private String facNm; // 제조업체 nm
    private String whsNm; // 창고 nm
    private String itemCatNm; // 제품 품목 ID
    private String itemNm; // 화장품 제품명
    private int inbItemVol; // 제품 용량
    private int inbItemPrice; // 제품 단가
    private int inbItemCnt; // 제품 수량
    private LocalDate inbItemExpirationDt; // 제품 유통기한

    public InboundDto(String selNm, String facNm, String whsNm, String inbItemCatNM, String inbItemNM, int inbItemVol, int inbItemPrice, int inbItemCnt, LocalDate inbItemExpirationDt) {
        this.selNm = selNm;
        this.facNm = facNm;
        this.whsNm = whsNm;
        this.itemNm = inbItemNM;
        this.itemCatNm = inbItemCatNM;
        this.inbItemVol = inbItemVol;
        this.inbItemPrice = inbItemPrice;
        this.inbItemCnt = inbItemCnt;
        this.inbItemExpirationDt = inbItemExpirationDt;
    }


}
