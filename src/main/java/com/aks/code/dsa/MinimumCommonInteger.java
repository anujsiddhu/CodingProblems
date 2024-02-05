package com.aks.code.dsa;

import java.util.*;

public class MinimumCommonInteger {
    public static void main(String[] args) {
       int[] data = new int[] {2,4,2,3,5};
       int[] res = new int[data.length];
       Arrays.fill(res, Integer.MAX_VALUE);

       //===== logic ====
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < data.length; i++) {
            if(!map.containsKey(data[i])) {
                map.put(data[i], new ArrayList<>());
                map.get(data[i]).add(-1);
            }
            map.get(data[i]).add(i);
        }
        for(int k : map.keySet()) {
            map.get(k).add(data.length);
        }
        for(int d : map.keySet()) {
            List<Integer> val = map.get(d);
            int maxGap = 0;
            for(int i = 1; i<val.size(); i++) {
                maxGap = Integer.max(maxGap, val.get(i) - val.get(i - 1));
            }
            res[maxGap - 1] = Math.min(res[maxGap - 1], d);
        }
        for (int i = 1; i < res.length; i++) {
            res[i] = Math.min(res[i], res[i - 1]);
        }
        for (int i = 0; i < res.length; i++) {
            if(res[i] == Integer.MAX_VALUE) {
                res[i] = -1;
            }
        }
       //

       System.out.println(Arrays.toString(res));
    }
    
}
 

/**
 * Example
Given, n = 5, data = [4, 3, 3, 4, 2]

subarrays | minimun comon integer
[4][3][3][4][2] | -1
[4,3][3,3][3,4][4,2] | -1
[4,3,3],[3,3,4],[3,4,2] | 3
[4,3,3,4][3,3,4,2] | 3
[4,3,3,4,2] | 2

Hence, the algorithm will return the array [-1, -1, 3, 3, 2]
 */