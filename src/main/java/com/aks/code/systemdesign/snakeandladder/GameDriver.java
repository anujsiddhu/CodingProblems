package com.aks.code.systemdesign.snakeandladder;

public class GameDriver {
    public static void main(String[] args) {
        SnakesAndLadders game = new SnakesAndLadders(50);
        game.addPlayer(new Player(1, "P1"));
        game.addPlayer(new Player(2, "P2"));
        Player won = game.startGame();
        System.out.println(won);
    }
}
