package com.aks.code.systemdesign.chess;

public class King extends Pieces {

    protected King(PiecesColour piecesColour) {
        super(piecesColour);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
