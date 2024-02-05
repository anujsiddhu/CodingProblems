package com.aks.code.systemdesign.snakegame;

public enum Direction {
    NONE(0, 0),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int col;
    private final int row;

    Direction(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int col() {
        return col;
    }

    public int row() {
        return row;
    }
}
