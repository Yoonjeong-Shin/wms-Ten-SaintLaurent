package com.sh.model.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GbgDetailDto {
    private BigInteger itemDetailSerialNum; // 화장품 시리얼 번호
    private BigInteger itemPk; // 화장품 ID
    private BigInteger itemState; // 화장품 상태

    @Override
    public String toString() {
        return "GbgDetailDto{" +
                "itemDetailSerialNum=" + itemDetailSerialNum +
                ", itemPk=" + itemPk +
                ", itemState=" + itemState +
                '}';
    }
}
