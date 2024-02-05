package com.aks.code.systemdesign.deckofcards;

public class Card {
    private final int value;
    private final Suit suit;
    private boolean isAvailable;

    public Card(int value, Suit suit) {
        this.isAvailable = true;
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
