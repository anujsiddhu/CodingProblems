package com.aks.code.dsa;

import java.util.Arrays;

// https://leetcode.com/problems/xor-queries-of-a-subarray/
public class XORQueriesSubarray {

    public static void main(String[] args) {
        XORQueriesSubarray subarray = new XORQueriesSubarray();

        int[] arr = new int[]{1, 3, 4, 8};
        int[][] queries = new int[][]{
                {0, 1}, {1, 2}, {0, 3}, {3, 3}
        };

        System.out.println(Arrays.toString(subarray.xorQueries(arr, queries)));
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] pre = new int[n];
        int[] post = new int[n];

        pre[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre[i] = pre[i-1] ^ arr[i];
        }
        post[n-1] = arr[n-1];
        for(int i = n-2; i>= 0; i--) {
            post[i] = post[i+1] ^ arr[i];
        }

        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int s = queries[i][0];
            int e = queries[i][1];
            if(s == e) {
                res[i] = arr[s];
            } else {
                if(s == 0 && e == n-1) {
                    res[i] = pre[n-1];
                } else if(s == 0) {
                    res[i] = pre[n-1] ^ post[e+1];
                } else if(e == n-1) {
                    res[i] = pre[n-1] ^ pre[s-1];
                } else {
                    res[i] = pre[n-1] ^ pre[s-1] ^ post[e+1];
                }
            }
        }
        return res;
    }
}
