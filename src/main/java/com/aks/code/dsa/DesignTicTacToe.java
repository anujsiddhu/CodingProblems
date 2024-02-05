package com.aks.code.dsa;

// https://leetcode.ca/2016-11-12-348-Design-Tic-Tac-Toe/
public class DesignTicTacToe {
    private final int n;
    private final int[] colCount;
    private final int[] rowCount;
    private int digCount;
    private int revDigCount;

    public DesignTicTacToe(int n) {
        this.n = n;
        rowCount = new int[n];
        colCount = new int[n];
        digCount = 0;
        revDigCount = 0;
    }

    public int move(int row, int col, Players player) {
        if (row >= 0 && row < n && col >= 0 && col < n) {
            int value = player == Players.P1 ? 1 : -1;
            colCount[col] += value;
            rowCount[row] += value;

            if (row == col) {
                digCount += value;
            }
            if (row + col == n - 1) {
                revDigCount += value;
            }

            if (colCount[col] == n || rowCount[row] == n || digCount == n || revDigCount == n) {
                return player.v;
            }
        }
        return 0;
    }

    private enum Players {
        P1(1), P2(2);

        private final int v;

        Players(int v) {
            this.v = v;
        }
    }

    public static void main(String[] args) {
        DesignTicTacToe tacToe = new DesignTicTacToe(3);
        tacToe.move(0, 0, Players.P1);
        tacToe.move(0, 2, Players.P2);
        tacToe.move(2, 2, Players.P1);
        tacToe.move(1, 1, Players.P2);
        tacToe.move(2, 0, Players.P1);
        tacToe.move(1, 2, Players.P2);
        int win = tacToe.move(2, 1, Players.P1);
        System.out.println(win);
    }
}
