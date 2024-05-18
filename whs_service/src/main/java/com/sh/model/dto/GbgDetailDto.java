package com.sh.model.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GbgDetailDto {
    private long gbgDetailPk; // 폐기 ID
    private long itemPk; // 화장품 ID
    private int itemState; // 화장품 상태
    private String gbgSerialNum; // 화장품 시리얼 번호

    @Override
    public String toString() {
        return "GbgDetailDto{" +
                "gbgDetailPk=" + gbgDetailPk +
                ", itemIdPk=" + itemPk +
                ", itemState=" + itemState +
                ", gbgSerialNum='" + gbgSerialNum + '\'' +
                '}';
    }
}
