package com.example.trendyolshopping.model;

public class Coupon {

    private DiscountType discountType;
    private Integer discount;
    private Integer amount;

    public Coupon(Integer amount, Integer discount, DiscountType discountType) {
        this.discountType = discountType;
        this.discount = discount;
        this.amount = amount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
