package com.aks.code.systemdesign.vendingmachine;

import lombok.Data;

import java.util.List;

@Data
public class Bucket {

    private List<Product> products;
    private List<Coin> coins;

}
