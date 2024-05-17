package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OutboundDto {
    private int itemCount;
    private int cartNum;
    private List<PurchaseListDto> purchaseListDto;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public List<PurchaseListDto> getPurchaseListDto() {
        return purchaseListDto;
    }

    public void setPurchaseListDto(List<PurchaseListDto> purchaseListDto) {
        this.purchaseListDto = purchaseListDto;
    }

}
