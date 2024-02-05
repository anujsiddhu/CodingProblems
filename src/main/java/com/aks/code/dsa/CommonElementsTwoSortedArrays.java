package com.aks.code.dsa;

import java.util.ArrayList;

public class CommonElementsTwoSortedArrays {
    public static void main(String[] args) {
        // NOTE: The following input values are used for testing your solution.

        int[] array1A = {1, 3, 4, 6, 7, 9};
        int[] array2A = {1, 2, 4, 5, 9, 10};
        // commonElements(array1A, array2A) should return [1, 4, 9] (an array).
        Integer[] res = commonElements(array1A, array2A);
        for (int i : res) {
            System.out.println(i);
        }

        int[] array1B = {1, 2, 9, 10, 11, 12};
        int[] array2B = {0, 1, 2, 3, 4, 5, 8, 9, 10, 12, 14, 15};
        // commonElements(array1B, array2B) should return [1, 2, 9, 10, 12] (an array).

        int[] array1C = {0, 1, 2, 3, 4, 5};
        int[] array2C = {6, 7, 8, 9, 10, 11};
        // common_elements(array1C, array2C) should return [] (an empty array).
    }

    // Implement your solution below.
    // NOTE: Remember to return an Integer array, not an int array.
    public static Integer[] commonElements(int[] array1, int[] array2) {
        Integer[] resultInArray = new Integer[0];
        if(array1 == null || array1.length == 0 || array2 == null || array2.length == 0) {
            return resultInArray;
        }

        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while(i < array1.length && j < array2.length) {
            if(array1[i] == array2[j]) {
                list.add(array1[i]);
                i++;
                j++;
            } else if(array1[i]  < array2[j]) {
                i++;
            } else {
                j++;
            }
        }
        if(!list.isEmpty()) {
            resultInArray = new Integer[list.size()];
            int k = 0;
            for(int l : list) {
                resultInArray[k++] = l;
            }
        }
        return resultInArray;
    }
}
