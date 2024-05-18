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
    private String inbItemNM; // 화장품 제품명
    private int inbItemCatPk; // 제품 품목 ID
    private int inbItemVol; // 제품 용량
    private int inbItemPrice; // 제품 단가
    private int inbItemCnt; // 제품 수량
    private LocalDate inbItemExpirationDt; // 제품 유통기한

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
