package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailDto {
    private long itemDetailPk;
    private long itemIdPk;
    private int itemDetailStatus;
    private long locateIdPk;
    private LocalDate itemDetailExpirationDt;
    private String itemDetailSerialNum;
}
