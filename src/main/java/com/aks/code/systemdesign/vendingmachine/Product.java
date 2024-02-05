package com.aks.code.systemdesign.vendingmachine;

public enum Product {
    COKE(25),
    PEPSI(35),
    SODA(45);
    private final int price;

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
