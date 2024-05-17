package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private String itemName;
    private int itemVol;
    private int itemCnt;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemVol() {
        return itemVol;
    }

    public void setItemVol(int itemVol) {
        this.itemVol = itemVol;
    }

    public int getItemCnt() {
        return itemCnt;
    }

    public void setItemCnt(int itemCnt) {
        this.itemCnt = itemCnt;
    }
}
