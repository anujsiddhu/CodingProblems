package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/numbers-with-same-consecutive-differences/
public class NumbsSameConsDiff {
    public static void main(String[] args) {
        NumbsSameConsDiff diff = new NumbsSameConsDiff();
        int[] res = diff.numsSameConsecDiff(2, 1);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public int[] numsSameConsecDiff(int n, int k) {

        return solve1(n, k);
    }

    private int[] solve1(int n, int k) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            rec(i, k, n - 1, list);
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void rec(int nsf, int k, int n, List<Integer> list) {
        if (n <= 0) {
            list.add(nsf);
            return;
        }
        int ld = nsf % 10;
        for (int i = 0; i <= 9; i++) {
            if (Math.abs(ld - i) == k) {
                rec(nsf * 10 + i, k, n - 1, list);
            }
        }
    }
}
