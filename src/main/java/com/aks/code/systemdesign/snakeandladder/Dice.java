package com.aks.code.systemdesign.snakeandladder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;


@AllArgsConstructor
@Getter
public class Dice {
    private int minValue;
    private int maxValue;
    private int currentValue;

    public int roll() {
        currentValue = RandomUtils.nextInt(minValue, maxValue + 1);
        System.out.println("Dice roll number: " + currentValue);
        return currentValue;
    }
}
