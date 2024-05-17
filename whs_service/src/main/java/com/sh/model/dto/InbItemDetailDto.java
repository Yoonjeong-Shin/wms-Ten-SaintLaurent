package com.sh.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InbItemDetailDto {
    // Getter 및 Setter
    private String detailName;
    private String detailValue;

    // 기본 생성자
    public InbItemDetailDto() {
    }

    // 매개변수가 있는 생성자
    public InbItemDetailDto(String detailName, String detailValue) {
        this.detailName = detailName;
        this.detailValue = detailValue;
    }

}
