package com.sh.model.dto.json;

import com.sh.model.dto.json.InbDetailJsonDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InbJsonDto {
    private String sellerName;
    private String sellerLoc;
    private String factoryName;
    private String factoryLoc;
    private String cat;
    private String itemName;
    private int vol;
    private int price;
    private int itemCount;
    private LocalDate expirationDate;
    List<InbDetailJsonDto> itemsDetail;


}
