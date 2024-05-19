package com.sh.model.dto.json;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InbDetailJsonDto {
    private String itemSerialNum; // 각 화장품마다 고유한 시리얼 번호
    private int state; // 화장품 상태

    @Override
    public String toString() {
        return "InbDetailJsonDto{" +
                "itemSerialNum='" + itemSerialNum + '\'' +
                ", state=" + state +
                '}';
    }
}
