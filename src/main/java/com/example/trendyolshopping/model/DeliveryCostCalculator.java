package com.example.trendyolshopping.model;

import java.util.HashSet;
import java.util.Set;

public class DeliveryCostCalculator {

    private Double costPerDelivery = 0.0;
    private Double costPerProduct = 0.0;
    private Double fixedCost = 0.0;

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    /**
     * calculates the delivery cost
     */
    public Double calculateFor(ShoppingCart shoppingCart) {

        int numberOfDeliveries = shoppingCart.getResult().size();
        Set<Product> uniqueProducts = new HashSet<>();
        uniqueProducts.addAll(shoppingCart.getProductList());
        long numberOfProducts = uniqueProducts.size();
        System.out.println("numberOfProducts "+numberOfProducts+" numberOfDeliveries "+numberOfDeliveries);
        return costPerDelivery * numberOfDeliveries + costPerProduct * numberOfProducts + fixedCost;
    }
}
