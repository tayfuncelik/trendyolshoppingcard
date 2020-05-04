package com.example.trendyolshopping.model;

public class Category {//extends Product
    private Category category;//subcategory
    private String categoryTitle;
    //private List<Product> product;

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
        this.category = null;//no sub
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
/*
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }*/
}
