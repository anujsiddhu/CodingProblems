package com.aks.code.systemdesign.chess;

public class Queen extends Pieces {

    protected Queen(PiecesColour piecesColour) {
        super(piecesColour);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
