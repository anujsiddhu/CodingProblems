package com.aks.code.systemdesign.chess;

public class Knight extends Pieces {

    protected Knight(PiecesColour piecesColour) {
        super(piecesColour);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
