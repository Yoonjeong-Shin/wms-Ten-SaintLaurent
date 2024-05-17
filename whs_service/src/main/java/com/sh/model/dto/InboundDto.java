package com.sh.model.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundDto {
    private BigInteger inbIdPk; // 입고 ID
    private BigInteger selIdPk; // 유통업체 ID
    private BigInteger facIdPk; // 제조업체 ID
    private BigInteger whsIdPk; // 창고 ID
    private BigInteger inbItemIdPk; // 화장품 ID
    private BigInteger inbItemNM; // 화장품 제품명
    private BigInteger inbItemCatPk; // 제품 품목 ID
    private BigInteger inbItemVol; // 제품 용량
    private BigInteger inbItemPrice; // 제품 단가
    private BigInteger inbItemCnt; // 제품 수량
    private BigInteger inbItemExpirationDt; // 제품 유통기한
    private String itemSerialNum; // 각 화장품마다 고유한 시리얼 번호
    private int state; // 화장품 상태

    public InboundDto(BigInteger inbIdPk, BigInteger selIdPk, BigInteger facIdPk, BigInteger whsIdPk, BigInteger inbItemIdPk, BigInteger inbItemCatPk, BigInteger inbItemNM, BigInteger inbItemVol, BigInteger inbItemPrice, BigInteger inbItemCnt, BigInteger inbItemExpirationDt) {
        this.inbIdPk = inbIdPk;
        this.selIdPk = selIdPk;
        this.facIdPk = facIdPk;
        this.whsIdPk = whsIdPk;
        this.inbItemIdPk = inbItemIdPk;
        this.inbItemNM = inbItemNM;
        this.inbItemCatPk = inbItemCatPk;
        this.inbItemVol = inbItemVol;
        this.inbItemPrice = inbItemPrice;
        this.inbItemCnt = inbItemCnt;
        this.inbItemExpirationDt = inbItemExpirationDt;
    }

    @Override
    public String toString() {
        return "InboundDto{" +
                "inbIdPk=" + inbIdPk +
                ", selIdPk=" + selIdPk +
                ", facIdPk=" + facIdPk +
                ", whsIdPk=" + whsIdPk +
                ", inbItemIdPk=" + inbItemIdPk +
                ", inbItemNM=" + inbItemNM +
                ", inbItemCatPk=" + inbItemCatPk +
                ", inbItemVol=" + inbItemVol +
                ", inbItemPrice=" + inbItemPrice +
                ", inbItemCnt=" + inbItemCnt +
                ", inbItemExpirationDt=" + inbItemExpirationDt +
                ", itemSerialNum='" + itemSerialNum + '\'' +
                ", state=" + state +
                '}';
    }
}
