package com.aks.code.systemdesign.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<Product, Integer> products;

    public Inventory() {
        products = new HashMap<>();
        products.put(Product.PEPSI, 30);
        products.put(Product.COKE, 20);
        products.put(Product.SODA, 25);
    }

    public int getProductQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public void updateProductQuantity(Product product, int qty) {
        products.put(product, qty);
    }
}
