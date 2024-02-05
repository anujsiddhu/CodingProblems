package com.aks.code.systemdesign.snakegame;

public class SnakeGame {
    private final Snake snake;
    private final Board board;
    private Direction direction;
    private boolean gameOver;

    public SnakeGame(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // We need to update the game at regular intervals,
    // and accept user input from the Keyboard.
    public void update() {
        System.out.println("Going to update the game");
        if (!gameOver && direction != Direction.NONE) {
            Cell nextCell = getNextCell(snake.getHead());

            if (snake.checkCrash(nextCell)) {
                setDirection(Direction.NONE);
                gameOver = true;
            } else {
                snake.move(nextCell);
                if (nextCell.getCellStatus() == CellStatus.FOOD) {
                    snake.grow();
                    board.generateFood();
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition) {
        System.out.println("find next cell");
        int row = currentPosition.getRow() + direction.row();
        int col = currentPosition.getCol() + direction.col();
        return board.getCells()[row][col];
    }

    public static void main(String[] args) {

        System.out.println("Going to start game");

        Cell initPos = new Cell(0, 0);
        Snake initSnake = new Snake(initPos);
        Board board = new Board(10, 10);
        SnakeGame newGame = new SnakeGame(initSnake, board);
        newGame.gameOver = false;
        newGame.direction = Direction.RIGHT;

        for (int i = 0; i < 20 && !newGame.gameOver; i++) {
            if (i == 2) {
                newGame.board.generateFood();
            }
            newGame.update();
            if (i == 3) {
                newGame.direction = Direction.RIGHT;
            }

        }
    }
}
