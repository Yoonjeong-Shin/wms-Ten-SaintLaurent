package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundCheckDto {
    private int itemStatus;
    private int pickCnt;
    private int itemCnt;

}
