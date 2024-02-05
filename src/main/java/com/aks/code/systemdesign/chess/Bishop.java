package com.aks.code.systemdesign.chess;

public class Bishop extends Pieces {

    protected Bishop(PiecesColour piecesColour) {
        super(piecesColour);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
