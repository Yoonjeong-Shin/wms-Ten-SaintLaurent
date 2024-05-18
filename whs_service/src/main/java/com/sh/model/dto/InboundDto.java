package com.sh.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InboundDto {
    private long inbIdPk; // 입고 ID
    private long selIdPk; // 유통업체 ID
    private long facIdPk; // 제조업체 ID
    private long whsIdPk; // 창고 ID
    private long inbItemIdPk; // 화장품 ID
    private int inbItemCatPk; // 제품 품목 ID
    private String inbItemNM; // 화장품 제품명
    private int inbItemVol; // 제품 용량
    private int inbItemPrice; // 제품 단가
    private int inbItemCnt; // 제품 수량
    private LocalDate inbItemExpirationDt; // 제품 유통기한

    public InboundDto(String inbItemNM, int inbItemVol, int inbItemPrice, int inbItemCnt, LocalDate inbItemExpirationDt) {
        this.inbItemNM = inbItemNM;
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
                ", inbItemExpirationDt=" + inbItemExpirationDt
                ;
    }
}
