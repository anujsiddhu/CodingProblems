package com.aks.code.dsa;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/shortest-path-visiting-all-nodes/
public class ShortestPathLength {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(p -> graph[p].length));
        for (int i = 0; i < n; i++) {
            pq.add(i);
        }

        int res = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int start = pq.poll();
            int[] visited = new int[n];
            dfs(graph, visited, start);
            if (allVis(visited)) {
                res = Math.min(count(visited), res);
            }
        }

        return res != Integer.MIN_VALUE ? res : -1;
    }

    private void dfs(int[][] graph, int[] visited, int index) {
        visited[index]++;
        if (allVis(visited)) {
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(p -> graph[p].length));
        for (int ad : graph[index]) {
            pq.add(ad);
        }
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (!pq.isEmpty() && visited[curr] > 0) {
                continue;
            }
            if (!allVis(visited) && visited[curr] < graph[curr].length) {
                dfs(graph, visited, curr);
            }
        }
    }

    private int count(int[] visited) {
        int len = 0;
        for (int curr : visited) {
            len += curr;
        }
        return len - 1;
    }

    private boolean allVis(int[] visited) {
        for (int curr : visited) {
            if (curr == 0) return false;
        }
        return true;
    }
}