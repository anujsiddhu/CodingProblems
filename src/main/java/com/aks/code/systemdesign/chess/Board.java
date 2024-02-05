package com.aks.code.systemdesign.chess;

import lombok.Data;

@Data
public class Board {
    private final Box[][] boxes;

    public Board(int n) {
        boxes = new Box[n][n];
        initilize();
    }

    public void initilize() {
        // fill all box for both players
        boxes[0][0] = new Box(new Rook(PiecesColour.WHITE), 0, 0);
        boxes[0][1] = new Box(new Knight(PiecesColour.WHITE), 0, 1);
        // for all 32 , 16 for each players
    }

    public Box getBox(int x, int y) {
        return boxes[x][y];
    }
}
