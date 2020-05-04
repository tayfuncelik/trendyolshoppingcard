package com.example.trendyolshopping.model;

public class Campaign {

    int items;
    Category category;
    Double discountRatio;
    DiscountType discountType;


    public Campaign(Category category, Double discountRatio, int items, DiscountType discountType) {
        this.items = items;
        this.category = category;
        this.discountRatio = discountRatio;
        this.discountType = discountType;
    }

    public Double getDiscountRatio() {
        return discountRatio;
    }
}
