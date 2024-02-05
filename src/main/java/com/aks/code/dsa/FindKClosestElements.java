package com.aks.code.dsa;

// https://leetcode.com/problems/find-k-closest-elements/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKClosestElements {
    public static void main(String[] args) {
        FindKClosestElements fk = new FindKClosestElements();

        List<Integer> cl = fk.findClosestElements(new int[] {1,2,3,4,5}, 4, 3);

        System.out.println(cl);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> res = new ArrayList<>();
        int n = arr.length;
        int[] diff  = new int[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (n1, n2) -> {
            if (n2[0] == n1[0]) {
                return n2[1]-n1[1];
            } else if (n2[0] < n1[0]) {
                return -1;
            } else {
                return 1;
            }
        });

        for(int i = n-1; i>=0; i--) {
            diff[i] = Math.abs(arr[i] - x);
            pq.add(new int[] { diff[i], i});
            if(pq.size() > k) {
                pq.poll();
            }
        }
        PriorityQueue<int[]> pq2 = new PriorityQueue<>(k, (n1, n2) -> {
            if (n1[1] < n2[1]) {
                return -1;
            } else {
                return 1;
            }
        });
        while(!pq.isEmpty()) {
            pq2.add(pq.poll());
        }
        while(!pq2.isEmpty()) {
            int item = arr[pq2.poll()[1]];
            res.add(item);
        }

        return res;
    }
}
