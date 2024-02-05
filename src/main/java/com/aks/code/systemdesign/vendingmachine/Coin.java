package com.aks.code.systemdesign.vendingmachine;

public enum Coin {
    ONE(1),
    FIVE(5),
    TEN(10);
    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
