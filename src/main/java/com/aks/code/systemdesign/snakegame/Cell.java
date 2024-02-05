package com.aks.code.systemdesign.snakegame;

public class Cell {
    private final int row, col;
    private CellStatus cellStatus;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.setCellStatus(CellStatus.EMPTY);
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellType) {
        this.cellStatus = cellType;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
