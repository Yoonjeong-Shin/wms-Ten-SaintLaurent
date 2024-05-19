package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private String productName;
    private int productVolume;
    private int productCount;
}
