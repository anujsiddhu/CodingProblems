package com.aks.code.systemdesign.deckofcards;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public void addCard(Card card) {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        this.cards.add(card);
    }

    public int score() {
        int totalValue = 0;
        for (Card card : cards) {
            totalValue += card.getValue();
        }
        return totalValue;
    }
}
