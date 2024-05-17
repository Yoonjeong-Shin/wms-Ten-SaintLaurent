package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemDto {
    private BigInteger itemIdPk;
    private String itemNM;
    private String itemCat;
    private int itemVol;
    private BigInteger itemDetailPk;
    private int itemDetailStatus;
    private int locateIdPk;
    private LocalDate itemDetailExpirationDt;
    private String itemDetailSerialNum;
}
