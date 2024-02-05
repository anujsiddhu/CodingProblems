package com.aks.code.dsa;

import java.util.HashMap;
import java.util.Map;

// https://www.interviewbit.com/problems/subarray-with-given-xor/
public class SubArrayXOR {

    public static void main(String[] args) {
        SubArrayXOR arrayXOR = new SubArrayXOR();
        System.out.println(arrayXOR.solve(new int[]{4, 2, 2, 6, 4}, 6));
        System.out.println(arrayXOR.solve(new int[]{5, 6, 7, 8, 9}, 5));
    }


    public int solve(int[] A, int B) {
        int n = A.length;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int c = 0;

        for(int i = 0; i < n; i++) {
            pre = pre ^ A[i];
            int x = pre ^ B;

            if(map.containsKey(x)) {
                c += map.get(x);
            }
            map.put(pre, map.getOrDefault(pre, 0)+1);
        }

        return c;
    }
}
