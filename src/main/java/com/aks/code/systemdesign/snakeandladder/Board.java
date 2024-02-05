package com.aks.code.systemdesign.snakeandladder;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

@Data
public class Board {
    private final Cell[] cells;
    private final int size;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < size; i++) {
            int next = prepareNext(i);
            cells[i] = new Cell(i + 1, next);
        }
    }

    private int prepareNext(int i) {
        // ladder
        if (i == 1) return RandomUtils.nextInt(1, size);
        if (i == 9) return RandomUtils.nextInt(9, size);
        if (i == 28) return RandomUtils.nextInt(28, size);
        if (i == 51) return RandomUtils.nextInt(51, size);
        if (i == 80) return RandomUtils.nextInt(80, size);

        // snake
        if (i == 17) return RandomUtils.nextInt(1, 17);
        if (i == 55) return RandomUtils.nextInt(1, 55);
        if (i == 64) return RandomUtils.nextInt(1, 64);
        if (i == 81) return RandomUtils.nextInt(1, 81);
        if (i == 98) return RandomUtils.nextInt(1, 44);

        return i;
    }

    @Override
    public String toString() {
        return "Board{" +
                "cells=" + Arrays.toString(cells) +
                '}';
    }
}
