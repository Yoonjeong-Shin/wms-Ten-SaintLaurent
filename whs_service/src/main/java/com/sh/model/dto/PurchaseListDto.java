package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseListDto {
    private int orderId;
    private int userId;
    private List<ItemDto> itemDto;
    private int pickCnt;
    private int outboundId;
    private Date outboundDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ItemDto> getItemDto() {
        return itemDto;
    }

    public void setItemDto(List<ItemDto> itemDto) {
        this.itemDto = itemDto;
    }

    public int getPickCnt() {
        return pickCnt;
    }

    public void setPickCnt(int pickCnt) {
        this.pickCnt = pickCnt;
    }

    public int getOutboundId() {
        return outboundId;
    }

    public void setOutboundId(int outboundId) {
        this.outboundId = outboundId;
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }
}
