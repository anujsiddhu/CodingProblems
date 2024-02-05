package com.aks.code.systemdesign.chess;

public class Rook extends Pieces {

    protected Rook(PiecesColour piecesColour) {
        super(piecesColour);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
