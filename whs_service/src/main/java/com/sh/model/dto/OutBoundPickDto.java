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
public class OutBoundPickDto {
    private String itemSerialNum;
    private int itemStatus;
    private int itemId;
    private Date expirationDate;
    private Long cartId;

}
