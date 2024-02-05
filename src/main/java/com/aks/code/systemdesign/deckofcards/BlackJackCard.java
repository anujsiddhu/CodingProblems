package com.aks.code.systemdesign.deckofcards;

public class BlackJackCard extends Card {

    public BlackJackCard(int value, Suit suit) {
        super(value, suit);
    }

    public boolean isAce() {
        return this.getValue() == 1;
    }

    public boolean isFaceCard() {
        return 10 < this.getValue() && this.getValue() <= 13;
    }

    @Override
    public int getValue() {
        if (this.isAce()) {
            return 1;
        } else if (isFaceCard()) {
            return 10;
        } else {
            return this.getValue();
        }
    }


}
