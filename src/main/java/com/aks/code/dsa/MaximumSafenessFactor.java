package com.aks.code.dsa;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumSafenessFactor {
    int[][] move = new int[][] {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    int n;
    int res = 0;

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        int[][] safeness = build(grid);
        if (safeness[0][0] == 0 || safeness[n - 1][n - 1] == 0) {
            return res;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.add(new int[]{0, 0, safeness[0][0]});
        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;
        while (!pq.isEmpty()) {
            int size = pq.size();
            while (size-- > 0) {
                int[] curr = pq.poll();
                int i = curr[0];
                int j = curr[1];
                int cs = curr[2];

                if (i == n - 1 && j == n - 1) {
                    return cs;
                }
                for (int k = 0; k < 4; k++) {
                    int ni = i + move[k][0];
                    int nj = j + move[k][1];
                    if (inrange(ni, nj) && !vis[ni][nj]) {
                        pq.add(new int[]{ni, nj, Math.min(cs, safeness[ni][nj])});
                        vis[ni][nj] = true;
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(int[][] safeness, int i, int j, boolean[][] vis, int safe) {
        if (!inrange(i, j) || vis[i][j]) {
            return;
        }
        if (i == n - 1 && j == n - 1) {
            res = Integer.max(res, safe);
            return;
        }
        vis[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int ni = i + move[k][0];
            int nj = j + move[k][1];
            if (inrange(ni, nj) && !vis[ni][nj]) {
                dfs(safeness, ni, nj, vis, Integer.min(safeness[ni][nj], safe));
            }
        }
        vis[i][j] = false;
    }

    private int[][] build(List<List<Integer>> grid) {
        int[][] safeness = new int[n][n];
        boolean[][] vis = new boolean[n][n];
        Queue<int[]> qu = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    qu.add(new int[]{i, j, i, j});
                    vis[i][j] = true;
                }
            }
        }
        while (!qu.isEmpty()) {
            int[] top = qu.poll();
            int i = top[0];
            int j = top[1];
            int safei = top[2];
            int safej = top[3];

            for (int k = 0; k < 4; k++) {
                int ni = i + move[k][0];
                int nj = j + move[k][1];
                if (inrange(ni, nj) && !vis[ni][nj]) {
                    vis[ni][nj] = true;
                    int d = Math.abs(safei - ni) + Math.abs(safej - nj);
                    qu.add(new int[]{ni, nj, safei, safej});
                    safeness[ni][nj] = d;
                }
            }
        }
        return safeness;
    }

    private boolean inrange(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}
