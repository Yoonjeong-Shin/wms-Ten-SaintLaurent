package com.sh.model.dto;

import java.math.BigInteger;
import java.util.Objects;

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

    public InboundDto() {
    }

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

    public BigInteger getInboundID() {
        return inboundID;
    }

    public void setInboundID(BigInteger inboundID) {
        this.inboundID = inboundID;
    }

    public BigInteger getSelID() {
        return selID;
    }

    public void setSelID(BigInteger selID) {
        this.selID = selID;
    }

    public BigInteger getFacID() {
        return facID;
    }

    public void setFacID(BigInteger facID) {
        this.facID = facID;
    }

    public BigInteger getWhsID() {
        return whsID;
    }

    public void setWhsID(BigInteger whsID) {
        this.whsID = whsID;
    }

    public BigInteger getInbItemID() {
        return inbItemID;
    }

    public void setInbItemID(BigInteger inbItemID) {
        this.inbItemID = inbItemID;
    }

    public BigInteger getInbItemNM() {
        return inbItemNM;
    }

    public void setInbItemNM(BigInteger inbItemNM) {
        this.inbItemNM = inbItemNM;
    }

    public BigInteger getInbItemCat() {
        return inbItemCat;
    }

    public void setInbItemCat(BigInteger inbItemCat) {
        this.inbItemCat = inbItemCat;
    }

    public BigInteger getInbItemVol() {
        return inbItemVol;
    }

    public void setInbItemVol(BigInteger inbItemVol) {
        this.inbItemVol = inbItemVol;
    }

    public BigInteger getInbItemPrice() {
        return inbItemPrice;
    }

    public void setInbItemPrice(BigInteger inbItemPrice) {
        this.inbItemPrice = inbItemPrice;
    }

    public BigInteger getInbItemCnt() {
        return inbItemCnt;
    }

    public void setInbItemCnt(BigInteger inbItemCnt) {
        this.inbItemCnt = inbItemCnt;
    }

    public BigInteger getInbItemExpirationDt() {
        return inbItemExpirationDt;
    }

    public void setInbItemExpirationDt(BigInteger inbItemExpirationDt) {
        this.inbItemExpirationDt = inbItemExpirationDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InboundDto that = (InboundDto) o;
        return Objects.equals(inboundID, that.inboundID) && Objects.equals(selID, that.selID) && Objects.equals(facID, that.facID) && Objects.equals(whsID, that.whsID) && Objects.equals(inbItemID, that.inbItemID) && Objects.equals(inbItemNM, that.inbItemNM) && Objects.equals(inbItemCat, that.inbItemCat) && Objects.equals(inbItemVol, that.inbItemVol) && Objects.equals(inbItemPrice, that.inbItemPrice) && Objects.equals(inbItemCnt, that.inbItemCnt) && Objects.equals(inbItemExpirationDt, that.inbItemExpirationDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inboundID, selID, facID, whsID, inbItemID, inbItemNM, inbItemCat, inbItemVol, inbItemPrice, inbItemCnt, inbItemExpirationDt);
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
