package com.aks.code.systemdesign.snakeandladder;


import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    private final Queue<Player> players;
    private final Board board;
    private final Dice dice;
    private boolean gameOn = false;

    public SnakesAndLadders(int size) {
        players = new LinkedList<>();
        dice = new Dice(1, 6, 1);
        board = new Board(size);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player startGame() {
        gameOn = true;
        while (gameOn) {
            Player player = players.poll();
            int roll = player.roll(dice);
            int newVal = player.getPosition() + roll;
            if (newVal > board.getSize()) {
                players.add(player);
                continue;
            }
            if (newVal == board.getSize()) {
                player.setWon(true);
                gameOn = false;
                return player;
            }
            Cell cell = board.getCells()[newVal];
            int nextVal = cell.getNextPosition();
            player.setPosition(nextVal);
            players.add(player);
        }
        return null;
    }
}
