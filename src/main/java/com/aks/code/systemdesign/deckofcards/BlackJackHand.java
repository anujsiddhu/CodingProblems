package com.aks.code.systemdesign.deckofcards;

import java.util.ArrayList;
import java.util.List;

// https://github.com/donnemartin/system-design-primer/blob/master/solutions/object_oriented_design/deck_of_cards/deck_of_cards.ipynb
public class BlackJackHand extends Hand {
    private static final int BLACKJACK = 21;

    @Override
    public int score() {
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (int score : possibleScores()) {
            if (BLACKJACK < score && score < max) {
                max = score;
            } else if (min < score && score <= BLACKJACK) {
                min = score;
            }
        }
        if (min != Integer.MIN_VALUE) {
            return min;
        } else {
            return max;
        }
    }

    public List<Integer> possibleScores() {
        return new ArrayList<>();
    }
}
