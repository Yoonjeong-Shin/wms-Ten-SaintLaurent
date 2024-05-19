package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutBoundCartDto {
    private Long cartId;
    private Long outBoundId;
    private String itemName;
    private int pickCnt;
    private int cartCnt;
    private Date expirationDate;
}
