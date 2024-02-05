package com.aks.code.dsa;

// https://leetcode.com/discuss/interview-question/4366540/Google-or-Onsite-or-Insert-and-query-ranges

import java.util.TreeMap;

/**
 * use sorted intervals
 * on insert - insert new interval or merge with existing
 * on query - get find max start val <= q and check with end val
 */

public class InsertQueryRanges {
    public static void main(String[] args) {
        InsertQueryRanges queryRanges = new InsertQueryRanges();
        queryRanges.insert(2, 3);
        queryRanges.insert(9, 15);
        queryRanges.insert(11, 12);
        System.out.println(queryRanges.query(5));
        System.out.println(queryRanges.query(2));
        queryRanges.insert(8, 9);
        System.out.println(queryRanges.query(8));
        queryRanges.insert(0, 2);
        queryRanges.insert(8, 16);
        System.out.println(queryRanges.query(10));
    }

    private final TreeMap<Integer, Integer> map = new TreeMap<>();
    public void insert(int s, int e) {
        if (map.isEmpty()) {
            map.put(s, e);
        } else {
            Integer fk = map.floorKey(s);
            if (fk == null) {
                fk = map.ceilingKey(s);
            }
            while(fk != null && map.get(fk) != null && map.get(fk) < s) {
                fk = map.ceilingKey(map.get(fk));
            }
            if(fk == null) {
                map.put(s, e);
            } else {
                int fv = map.get(fk);
                map.remove(fk);
                map.put(Integer.min(fk, s), Integer.max(fv, e));
            }
        }
//        System.out.println(map);
    }

    public boolean query(int q) {
        Integer fk = map.floorKey(q);
        if (fk != null) {
            int val = map.get(fk);
            return q >= fk && q <= val;
        }
        return false;
    }
}
