package com.aks.code.systemdesign.chess;

import lombok.Data;

@Data
public abstract class Pieces {
    private PiecesColour piecesColour;
    private boolean isKilled;

    protected Pieces(PiecesColour piecesColour) {
        this.piecesColour = piecesColour;
    }

    public abstract boolean canMove(Board board, Box start, Box end);
}
