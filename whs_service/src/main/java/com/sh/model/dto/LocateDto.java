package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocateDto {
    private BigInteger id;
    private int itemCnt;
    private String lpnCode;
}
