package com.aks.code.dsa;

import java.util.Arrays;

// https://leetcode.com/problems/diagonal-traverse/
public class DiagonalTraverse {

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };

        int[] res = findDiagonalOrder(mat);
        System.out.println(Arrays.toString(res));
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = 0;

        boolean direction = true;
        int[] result = new int[n * m];
        int ri = 0;

        while (i < n && j < m) {
            result[ri++] = matrix[i][j];

            int ni = i + (direction ? -1 : 1);
            int nj = j + (direction ? 1 : -1);

            if (ni < 0 || ni == n || nj < 0 || nj == m) {
                if (direction) {
                    i += (j == m - 1 ? 1 : 0);
                    j += (j < m - 1 ? 1 : 0);
                } else {
                    j += (i == n - 1 ? 1 : 0);
                    i += (i < n - 1 ? 1 : 0);
                }
                direction = !direction;
            } else {
                i = ni;
                j = nj;
            }
        }
        return result;
    }

}
