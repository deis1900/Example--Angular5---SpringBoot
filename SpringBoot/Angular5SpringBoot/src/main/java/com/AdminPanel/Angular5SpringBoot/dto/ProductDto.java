package com.AdminPanel.Angular5SpringBoot.dto;

import com.AdminPanel.Angular5SpringBoot.model.Product;

import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable{
    private List<Product> products;

    public List<Product> getListProducts() {
        return products;
    }

    public void setListProducts(List<Product> products) {
        this.products = products;
    }
}
