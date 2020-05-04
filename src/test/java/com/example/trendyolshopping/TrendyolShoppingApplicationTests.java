package com.example.trendyolshopping;

import com.example.trendyolshopping.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TrendyolShoppingApplicationTests {

    final private double fixedCost = 2.99;
    final private double costPerDelivery = 1;
    final private double costPerProduct = 1;

    @Test
    void apply_Both_Coupon_And_Campaign_Discount() {

        Category category = new Category("food");
        Product apple = new Product("Apple", 100.0, category);
        Product almond = new Product("Almonds", 150.0, category);
        Product banana = new Product("Banana", 150.0, category);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(banana, 2);

        Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(category, 50.0, 5, DiscountType.Rate);
        Campaign campaign3 = new Campaign(category, 5.0, 5, DiscountType.Amount);
        Coupon coupon = new Coupon(100, 10, DiscountType.Rate);

        calculate(cart, campaign1, campaign2, campaign3, coupon);
        Assert.assertNotSame(cart.getCouponDiscount(), new Double(0.0));

    }

    @Test
    void apply_Only_Campaign_Discount() {

        Category category = new Category("food");
        Product apple = new Product("Apple", 100.0, category);
        Product almond = new Product("Almonds", 150.0, category);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(category, 50.0, 5, DiscountType.Rate);
        Campaign campaign3 = new Campaign(category, 5.0, 5, DiscountType.Amount);
        Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
        calculate(cart, campaign1, campaign2, campaign3, coupon);

        Assert.assertEquals(cart.getCouponDiscount(), new Double(0.0));
    }

    @Test
    void multiCategory() {

        Category category = new Category("food");
        Category categor2 = new Category("jewelry");
        Product apple = new Product("Apple", 100.0, category);
        Product watch = new Product("watch", 150.0, categor2);
        Product ring = new Product("ring", 10.0, categor2);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(watch, 4);
        cart.addItem(ring, 6);


        Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(category, 50.0, 5, DiscountType.Rate);
        Campaign campaign3 = new Campaign(category, 5.0, 5, DiscountType.Amount);
        Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
        calculate(cart, campaign1, campaign2, campaign3, coupon);

        Assert.assertNotSame(cart.getCouponDiscount(), new Double(0.0));
    }
    private void calculate(ShoppingCart cart, Campaign campaign1, Campaign campaign2, Campaign campaign3, Coupon coupon) {

        cart.applyDiscounts(campaign1, campaign2, campaign3);
        cart.applyCoupon(coupon);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);

        Double deliverCost = deliveryCostCalculator.calculateFor(cart);
        cart.setDeliveryCost(deliverCost);

        cart.print();
        System.out.println("CouponDiscount: " + cart.getCouponDiscount());
        System.out.println("CampaignDiscount: " + cart.getCampaignDiscount());
        System.out.println("TotalPrice: " + cart.getTotalPrice());
        System.out.println("TotalAmount: " + cart.getTotalAmountAfterDiscounts());
        System.out.println("DeliveryCost: " + cart.getDeliveryCost());
    }

}
