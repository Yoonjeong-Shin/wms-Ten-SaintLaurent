package com.sh.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutItemDetailDto {
    private int id;
    private int outItemId;
    private String outItemName;
    private String outItemDesc;
    private int outItemPrice;
    private int outItemQuantity;

}
