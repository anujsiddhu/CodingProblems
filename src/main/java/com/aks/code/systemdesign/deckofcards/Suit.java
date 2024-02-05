package com.aks.code.systemdesign.deckofcards;

public enum Suit {
    HEART(0),
    DIAMOND(1),
    CLUBS(2),
    SPADE(3);

    final int value;

    Suit(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
