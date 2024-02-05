package com.aks.code.dsa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {
    static int n = 3;
    static char[][] board = new char[n][n];
    private static char currentPlayer = 'X';

    public static void main(String[] args) throws Exception {
        initializeBoard(n);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("current player: " + currentPlayer);
            System.out.println("choose row and column");
            String line = br.readLine();
            String[] numbers = line.split("\\s+");
            int r = Integer.parseInt(numbers[0]);
            int c = Integer.parseInt(numbers[1]);
            boolean isValid = placeMark(r, c);
            if (!isValid) {
                System.out.println("invalid input by " + currentPlayer);
                if (checkForWin()) {
                    break;
                }
            }
            printBoard();
            if (isBoardFull())
                break;
            changePlayer();
        }
    }

    private static void initializeBoard(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static boolean placeMark(int row, int col) {
        if (row >= 0 && row < n && col >= 0 && col < n && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    private static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                    break;
                }
            }
        }
        return isFull;
    }

    public static boolean checkForWin() {
        if (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin()) {
            System.out.println("Player :-" + currentPlayer + " Won the game");
            return true;
        }
        return false;
    }

    private static boolean checkRowsForWin() {
        for (int i = 0; i < n; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumnsForWin() {
        for (int i = 0; i < n; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]))
                || (checkRowCol(board[0][2], board[1][1], board[2][0])));
    }

    private static boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    public static void changePlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}