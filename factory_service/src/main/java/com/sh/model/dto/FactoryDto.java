package com.sh.model.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FactoryDto {
    private Long itemId;
    private String sellerNm;
    private String itemCat;
    private String itemNm;
    private int itemVol;
    private Date expirationDate;
    private int itemPrice;
    private int prodCnt;
    private String field;


    List<FactoryDto2> factoryDto2;
}
