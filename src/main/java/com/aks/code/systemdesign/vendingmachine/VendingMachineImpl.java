package com.aks.code.systemdesign.vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

    private Inventory inventory;
    private int balance;
    private Product selectedProduct;

    public VendingMachineImpl(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public int getProductPrice(Product product) {
        selectedProduct = product;
        return product.getPrice();
    }

    @Override
    public void insertCoin(Coin coin) {
        balance += coin.getValue();
    }

    @Override
    public List<Coin> refund() {
        List<Coin> coins = new ArrayList<>();
        Coin[] sortedCoins = Coin.values();
        Arrays.sort(sortedCoins, (c1, c2) -> c2.getValue() - c1.getValue());
        for (Coin coin : sortedCoins) {
            while (balance > 0 && balance / coin.getValue() > 0) {
                coins.add(coin);
                balance = balance - coin.getValue();
            }
        }
        return coins;
    }

    @Override
    public Bucket collect() throws Exception {
        if (selectedProduct == null) {
            throw new Exception("Item not selected");
        }
        if (inventory.getProductQuantity(selectedProduct) <= 0) {
            throw new Exception("Product SoldOut");
        }
        if (balance < selectedProduct.getPrice()) {
            throw new Exception("Low balance");
        }
        Bucket bucket = new Bucket();
        bucket.setProducts(List.of(selectedProduct));
        inventory.updateProductQuantity(selectedProduct, inventory.getProductQuantity(selectedProduct) - 1);
        balance = balance - selectedProduct.getPrice();
        bucket.setCoins(refund());
        return bucket;
    }

    @Override
    public void reset() {
        inventory = new Inventory();
        balance = 0;
        selectedProduct = null;
    }
}
