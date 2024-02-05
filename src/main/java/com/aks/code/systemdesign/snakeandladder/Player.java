package com.aks.code.systemdesign.snakeandladder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Player {
    private final long id;
    private final String name;
    private int position = 0;
    private boolean won = false;

    public int roll(final Dice dice) {
        System.out.print(name + " ");
        return dice.roll();
    }

}
