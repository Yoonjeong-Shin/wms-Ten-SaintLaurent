package com.sh.model.dto;

import lombok.*;

import java.time.LocalDate;
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
    private LocalDate expirationDate;
    private int itemPrice;
    private int prodCnt;
    private String field;


    List<FactoryDto2> factoryDto2;
}
