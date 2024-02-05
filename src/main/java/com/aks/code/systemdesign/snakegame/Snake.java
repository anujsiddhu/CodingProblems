package com.aks.code.systemdesign.snakegame;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<Cell> snakePartList = new LinkedList<>();
    private Cell head;

    public Snake(Cell initPos) {
        head = initPos;
        snakePartList.add(head);
        head.setCellStatus(CellStatus.SNAKE);
    }

    public void grow() {
        snakePartList.add(head);
    }

    public void move(Cell nextCell) {
        System.out.println("Snake is moving to " + nextCell.getRow() + " " + nextCell.getCol());
        Cell tail = snakePartList.removeLast();
        tail.setCellStatus(CellStatus.EMPTY);

        head = nextCell;
        head.setCellStatus(CellStatus.SNAKE);
        snakePartList.addFirst(head);
    }

    public boolean checkCrash(Cell nextCell) {
        System.out.println("check for Crash");
        for (Cell cell : snakePartList) {
            if (cell == nextCell) {
                return true;
            }
        }
        return false;
    }

    public Cell getHead() {
        return head;
    }
}
