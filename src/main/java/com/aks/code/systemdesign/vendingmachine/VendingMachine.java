package com.aks.code.systemdesign.vendingmachine;

import java.util.List;

public interface VendingMachine {

     int getProductPrice(Product product);
     void insertCoin(Coin coin);
     List<Coin> refund();
     Bucket collect() throws Exception;
     void reset();
}
