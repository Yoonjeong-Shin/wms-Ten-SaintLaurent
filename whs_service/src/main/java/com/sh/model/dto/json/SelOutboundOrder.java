package com.sh.model.dto.json;

import java.time.LocalDate;

public class SelOutboundOrder {
    private Long id; // SEL_OUTB_ORDER_PK
    private String sellerName; // SEL_OUTB_ORDER_SELLER_NM
    private String category; // SEL_OUTB_ORDER_CAT
    private String itemName; // SEL_OUTB_ORDER_ITEM_NM
    private int volume; // SEL_OUTB_ORDER_VOL
    private LocalDate expirationDate; // SEL_OUTB_ORDER_EXPIRATION_DT
    private int price; // SEL_OUTB_ORDER_PRICE
    private Integer productCount; // SEL_OUTB_ORDER_PROD_CNT, nullable

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "SelOutboundOrder{" +
                "id=" + id +
                ", sellerName='" + sellerName + '\'' +
                ", category='" + category + '\'' +
                ", itemName='" + itemName + '\'' +
                ", volume=" + volume +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                ", productCount=" + productCount +
                '}'+"\n";
    }
}
