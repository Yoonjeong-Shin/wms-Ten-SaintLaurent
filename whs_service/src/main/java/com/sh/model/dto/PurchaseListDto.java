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
    private List<ItemDto> itemDtoList;
    private int pickCnt;
    private int outboundId;
    private Date outboundDate;

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }
}
