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
    private String itemDetailSerialNum;
    private long itemPk;
    private int itemDetailStatus;
    private long locatePk;
    private LocalDate itemDetailExpirationDt;
}
