package com.aks.code.dsa;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentlyOccurringItem {
    public static void main(String[] args) {
        // NOTE: The following input values are used for testing your solution.

        // mostFrequent(array1) should return 1.
        int[] array1 = {1, 3, 1, 3, 2, 1};
        // mostFrequent(array2) should return 3.
        System.out.println(mostFreqent(array1));

        int[] array2 = {3, 3, 1, 3, 2, 1};
        // mostFrequent(array3) should return null.
        System.out.println(mostFreqent(array2));

        int[] array3 = {};
        // mostFrequent(array4) should return 0.
        System.out.println(mostFreqent(array3));

        int[] array4 = {0};
        // mostFrequent(array5) should return -1.
        System.out.println(mostFreqent(array4));

        int[] array5 = {0, -1, 10, 10, -1, 10, -1, -1, -1, 1};
        System.out.println(mostFreqent(array5));
    }

    // Implement your solution below.
    public static Integer mostFreqent(int[] givenArray) {
        Integer maxItem = null;
        if(givenArray == null || givenArray.length == 0) {
            return maxItem;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : givenArray) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        maxItem = givenArray[0];
        for(int i = 1; i < givenArray.length; i++) {
            if(map.get(givenArray[i]) > map.get(maxItem)) {
                maxItem = givenArray[i];
            }

        }
        return maxItem;
    }
}
