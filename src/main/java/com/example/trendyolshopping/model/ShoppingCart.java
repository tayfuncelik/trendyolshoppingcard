package com.example.trendyolshopping.model;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {

    private Double totalPrice = 0.0;//total price for products even we apply discount will not change
    private Double totalAmount = 0.0;//Total amount of product
    private Double totalDiscounts = 0.0;
    private Double couponDiscount = 0.0;
    private Double campaignDiscount = 0.0;
    private Double deliveryCost = 0.0;
    private Map<Category, List<Product>> result;
    private List<Product> productList = new ArrayList<>();

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void addItem(Product product, Integer quantity) {

        for (int i = 0; i < quantity; i++) {
            this.productList.add(product);
        }
        this.totalPrice = this.totalPrice + product.getPrice();
        this.totalAmount = this.totalPrice;
//        result = productList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        result = productList.stream().collect(Collectors.groupingBy(Product::getCategory));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Map<Category, List<Product>> getResult() {
        return result;
    }

    public void applyDiscounts(Campaign campaign1, Campaign campaign2, Campaign campaign3) {
        List<Campaign> list = new ArrayList<>();
        list.add(campaign1);
        list.add(campaign2);
        list.add(campaign3);

        List<Campaign> amountDiscount = list.stream().filter(item -> item.discountType == DiscountType.Amount).collect(Collectors.toList());
        List<Campaign> rateDiscount = list.stream().filter(item -> item.discountType == DiscountType.Rate).collect(Collectors.toList());

        if (amountDiscount.size() > 0) {
            for (Campaign item : amountDiscount) {
                if (totalAmount >= totalDiscounts) {
                    totalAmount = totalAmount - item.getDiscountRatio();
                    campaignDiscount = campaignDiscount + item.getDiscountRatio();
                    System.out.println("Discount Amounth applied: " + totalAmount);
                }
            }
        }
        if (rateDiscount.size() > 0) {
            for (Campaign item : rateDiscount) {
                if (totalAmount >= totalDiscounts) {
                    totalAmount = totalAmount - totalAmount * item.getDiscountRatio() / 100;
                    campaignDiscount = campaignDiscount + totalAmount * item.getDiscountRatio() / 100;
                    System.out.println("Discount Ratio applied: " + totalAmount);
                }
            }
        }
    }

    public void applyCoupon(Coupon coupon) {
        if (totalAmount >= coupon.getAmount()) {
            System.out.println("Before applied Coupon " + totalAmount);
            if (coupon.getDiscountType() == DiscountType.Amount) {
                totalAmount = totalAmount - totalAmount * (coupon.getDiscount() / 100);
                couponDiscount = couponDiscount + totalAmount * (coupon.getDiscount() / 100);
                System.out.println("Coupon amount applied: " + totalAmount);
            } else {//RATE
                totalAmount = totalAmount - coupon.getDiscount();
                couponDiscount = couponDiscount + coupon.getDiscount();
                System.out.println("Coupon rate applied: " + totalAmount);
            }
        } else {
            System.out.println("Coupon can not be applied");
        }
    }

    public void print() {

        Set<Product> uniqueProducts= new HashSet<>();
        result.forEach((category, products) -> {
            uniqueProducts.addAll(products);
            System.out.print("CategoryName : " + category.getCategoryTitle());
            System.out.println("/Quantity: " + products.size());
        });

        for (Product p:uniqueProducts) {
            System.out.print("ProductName: " + p.getTitle());
            System.out.println("/Unit Price: " + p.getPrice());
        }
    }

    public Double getTotalAmountAfterDiscounts() {
        return totalAmount;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public Double getCampaignDiscount() {
        return campaignDiscount;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
