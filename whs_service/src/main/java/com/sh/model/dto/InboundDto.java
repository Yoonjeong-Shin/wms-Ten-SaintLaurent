package com.sh.model.dto;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InboundDto {
    private BigInteger inbIdPk; // 입고 ID
    private BigInteger selIdPk; // 유통업체 ID
    private BigInteger facIdPk; // 제조업체 ID
    private BigInteger whsIdPk; // 창고 ID
    private BigInteger inbItemIdPk; // 화장품 ID
    private String inbItemNM; // 화장품 제품명
    private int inbItemCatPk; // 제품 품목 ID
    private int inbItemVol; // 제품 용량
    private int inbItemPrice; // 제품 단가
    private int inbItemCnt; // 제품 수량
    private Date inbItemExpirationDt; // 제품 유통기한
    private String itemSerialNum; // 각 화장품마다 고유한 시리얼 번호
    private int state; // 화장품 상태

    public InboundDto(BigInteger inbIdPk, BigInteger selIdPk, BigInteger facIdPk, BigInteger whsIdPk, BigInteger inbItemIdPk, int inbItemCatPk, String inbItemNM, int inbItemVol, int inbItemPrice, int inbItemCnt, Date inbItemExpirationDt) {
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
