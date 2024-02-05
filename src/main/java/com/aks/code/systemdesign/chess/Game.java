package com.aks.code.systemdesign.chess;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private boolean gameOn;
    private final Player turn;

    public Game(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        board = new Board(8);

        if (player1.getSide() == PiecesColour.WHITE) {
            turn = player1;
        } else {
            turn = player2;
        }
    }

    public boolean playeMove(Player player, int sx, int sy, int ex, int ey) {
        Box start = board.getBox(sx, sy);
        Box end = board.getBox(ex, ey);


        return false;
    }

    public static void main(String[] args) {
        Player p1 = new Player("P1", PiecesColour.WHITE);
        Player p2 = new Player("P2", PiecesColour.BLACK);
        Game game = new Game(p1, p2);
        Player player = game.turn;

        while (game.gameOn) {
            boolean canMove = game.playeMove(player, 0,0,0,0);
            if(canMove) {

            } else {
                game.gameOn = false;
            }
        }
    }
}
