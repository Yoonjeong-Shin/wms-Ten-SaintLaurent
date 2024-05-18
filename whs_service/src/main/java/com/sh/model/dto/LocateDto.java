package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocateDto {
    private long locatePk;
    private int locateItemCnt;
    private String locateLpnCode;
    private long whsPk;
}
