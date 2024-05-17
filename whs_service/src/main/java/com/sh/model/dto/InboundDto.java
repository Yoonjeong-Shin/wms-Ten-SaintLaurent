package com.sh.model.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundDto {
    private BigInteger inboundID; // 입고 ID
    private BigInteger selID; // 유통업체 ID
    private BigInteger facID; // 제조업체 ID
    private BigInteger whsID; // 창고 ID
    private BigInteger inbItemID; // 화장품 ID
    private BigInteger inbItemNM; // 화장품 제품명
    private BigInteger inbItemCat; // 제품 품목
    private BigInteger inbItemVol; // 제품 용량
    private BigInteger inbItemPrice; // 제품 단가
    private BigInteger inbItemCnt; // 제품 수량
    private BigInteger inbItemExpirationDt; // 제품 유통기한
    private String itemSerialNum; // 각 화장품마다 고유한 시리얼 번호
    private int state; // 화장품 상태

    public InboundDto(BigInteger inboundID, BigInteger selID, BigInteger facID, BigInteger whsID, BigInteger inbItemID, BigInteger inbItemNM, BigInteger inbItemCat, BigInteger inbItemVol, BigInteger inbItemPrice, BigInteger inbItemCnt, BigInteger inbItemExpirationDt) {
        this.inboundID = inboundID;
        this.selID = selID;
        this.facID = facID;
        this.whsID = whsID;
        this.inbItemID = inbItemID;
        this.inbItemNM = inbItemNM;
        this.inbItemCat = inbItemCat;
        this.inbItemVol = inbItemVol;
        this.inbItemPrice = inbItemPrice;
        this.inbItemCnt = inbItemCnt;
        this.inbItemExpirationDt = inbItemExpirationDt;
    }

    @Override
    public String toString() {
        return "InboundDto{" +
                "inboundID=" + inboundID +
                ", selID=" + selID +
                ", facID=" + facID +
                ", whsID=" + whsID +
                ", inbItemID=" + inbItemID +
                ", inbItemNM=" + inbItemNM +
                ", inbItemCat=" + inbItemCat +
                ", inbItemVol=" + inbItemVol +
                ", inbItemPrice=" + inbItemPrice +
                ", inbItemCnt=" + inbItemCnt +
                ", inbItemExpirationDt=" + inbItemExpirationDt +
                '}';
    }
}
