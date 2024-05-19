package com.sh.model.dto.json;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SelInboundOrder {
    // Getters and setters
    private Long id;
    private String sellerName;
    private String factoryName;
    private String category;
    private String itemName;
    private int volume;
    private LocalDate expirationDate;
    private int price;
    private Integer productCount; // Nullable
    @Override
    public String toString() {
        return "SelInboundOrder{" +
                "id=" + id +
                ", sellerName='" + sellerName + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", category='" + category + '\'' +
                ", itemName='" + itemName + '\'' +
                ", volume=" + volume +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                ", productCount=" + productCount +
                '}'+"\n";
    }
}

