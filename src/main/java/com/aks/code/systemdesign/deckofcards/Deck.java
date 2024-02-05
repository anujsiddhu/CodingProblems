package com.aks.code.systemdesign.deckofcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private int dealIndex;

    public Deck() {
        cards = new ArrayList<>();
        for (int v = 1; v <= 13; v++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(v, suit));
            }
        }
        dealIndex = 0;
    }

    public int remainingCards() {
        return cards.size() - dealIndex;
    }

    public Card dealCard() {
        if (remainingCards() <= 0) {
            throw new RuntimeException("dealIndex out of bound");
        }
        Card card = cards.get(dealIndex);
        card.setAvailable(false);
        dealIndex++;
        return card;
    }


    public void shuffle() {
        Collections.shuffle(cards);
        dealIndex = 0;
    }
}
