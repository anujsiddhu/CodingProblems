package com.aks.code.dsa;

import java.util.HashMap;
import java.util.Map;

public class BiValuedArray {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {4,2,2,4,2}));
        System.out.println(solution(new int[] {1,5,4,5}));
        System.out.println(solution(new int[] {1,2,3,2,1,4,1,2,1,3,1}));

        System.out.println(solution(new int[] {1, 2, 3, 2}));
        System.out.println(solution(new int[] {0, 5, 4, 4, 5, 12}));

        System.out.println(solution(new int[] {1, 2, 3, 4, 5, 4,5,4}));
    }

    public static int solution(int[] nums) {
        int res = 1;
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        while(j < nums.length) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            while(map.size() > 2 && i <=j) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) - 1);
                if(map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }
            res = Integer.max(res, j-i+1);
            j++;
        }

        return res;
    }
}
