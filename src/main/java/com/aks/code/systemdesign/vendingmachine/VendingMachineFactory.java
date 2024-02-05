package com.aks.code.systemdesign.vendingmachine;

public class VendingMachineFactory {

    public static VendingMachine creteVendingMachine(Inventory inventory) {
        return new VendingMachineImpl(inventory);
    }
}
