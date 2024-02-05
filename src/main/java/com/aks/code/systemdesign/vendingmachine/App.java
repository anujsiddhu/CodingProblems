package com.aks.code.systemdesign.vendingmachine;

public class App {

    public static void main(String[] args) throws Exception {
        VendingMachine vendingMachine = VendingMachineFactory.creteVendingMachine(new Inventory());

        System.out.println("refund " + vendingMachine.refund());
        System.out.println("COKE price " + vendingMachine.getProductPrice(Product.COKE));
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.ONE);
        System.out.println("Collect " + vendingMachine.collect());
    }
}
