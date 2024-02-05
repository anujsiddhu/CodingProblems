package com.aks.code.dsa;

import java.util.Arrays;
import java.util.List;

public class ReachDestWithCost {

    public static void main(String[] args) {
        int mc = minCost(4, 4, 0, 1, 3, 4, List.of(1, 2, 3), List.of(4, 5, 6));
        System.out.println(mc);
    }

    /*
    m = 4
    n = 4
    si = 0
    sj = 1
    ei = 3
    ej = 4
    costRows - [1,2,3]
    costCols - [4,5,6]
     */

    static int[][] mat;

    static List<Integer> cr, cc;

    public static int minCost(int m, int n, int si, int sj, int ei, int ej,
                              List<Integer> costRows, List<Integer> costCols) {
        mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(mat[i], Integer.MAX_VALUE);
        }

        cr = costRows;
        cc = costCols;

        cr.add(Integer.MAX_VALUE);
        cc.add(Integer.MAX_VALUE);

        dfs(m-1, n-1, si, sj, ei, ej, 0);

        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(mat[i]));

        }
        return 0; //mat[ei][ej];
    }

    private static void dfs(int m, int n, int i, int j, int ei, int ej, int cost) {
        if (i == ei && j == ej) {
            mat[i][j] = Integer.min(mat[i][j], cost);
            return;
        }
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        System.out.println(i + " " + j);
        if (i > 0 && mat[i - 1][j] > cost + cr.get(i)) {
            mat[i - 1][j] = cost + cr.get(i);
            dfs(m, n, i - 1, j, ei, ej, mat[i + 1][j]);
        }
        if ( mat[i + 1][j] > cost + cr.get(i)) {
            mat[i + 1][j] = cost + cr.get(i);
            dfs(m, n, i + 1, j, ei, ej, mat[i + 1][j]);
        }
        if (j >  0 && mat[i][j - 1] > cost + cc.get(j)) {
            mat[i][j - 1] = cost + cc.get(j);
            dfs(m, n, i, j - 1, ei, ej, mat[i][j - 1]);
        }
        if (  mat[i][j + 1] > cost + cc.get(j)) {
            mat[i][j + 1] = cost + cc.get(j);
            dfs(m, n, i, j + 1, ei, ej, mat[i][j + 1]);
        }
    }
}
