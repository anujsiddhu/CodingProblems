package com.aks.code.systemdesign.chess;

public class Pawn extends Pieces {

    protected Pawn(PiecesColour piecesColour) {
        super(piecesColour);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
