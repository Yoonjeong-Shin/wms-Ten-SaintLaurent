package com.sh.model.dto.json;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Override
    public String toString() {
        return "InbJsonDto{" +
                "sellerName='" + sellerName + '\'' +
                ", sellerLoc='" + sellerLoc + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", factoryLoc='" + factoryLoc + '\'' +
                ", cat='" + cat + '\'' +
                ", itemName='" + itemName + '\'' +
                ", vol=" + vol +
                ", price=" + price +
                ", itemCount=" + itemCount +
                ", expirationDate=" + expirationDate +
                ", itemsDetail=" + itemsDetail +
                '}';
    }
}
