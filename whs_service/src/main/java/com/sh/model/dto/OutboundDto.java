package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OutboundDto {
    private int itemCnt;
    private int cartId;
    private List<PurchaseListDto> purchaseListDto;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemCnt() {
        return itemCnt;
    }

    public void setItemCnt(int itemCnt) {
        this.itemCnt = itemCnt;
    }

    public List<PurchaseListDto> getPurchaseListDto() {
        return purchaseListDto;
    }

    public void setPurchaseListDto(List<PurchaseListDto> purchaseListDto) {
        this.purchaseListDto = purchaseListDto;
    }

}
