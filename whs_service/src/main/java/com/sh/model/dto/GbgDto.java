package com.sh.model.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GbgDto {
    private BigInteger gbgPk; // 화장품 ID
    private String itemNM; // 화장품명
    private int itemVol; // 용량
    private int itemCatPk; // 아이템 품목ID

    @Override
    public String toString() {
        return "GbgDto{" +
                "gbgPk=" + gbgPk +
                ", itemNM='" + itemNM + '\'' +
                ", itemVol=" + itemVol +
                ", itemCatPk=" + itemCatPk +
                '}';
    }
}
