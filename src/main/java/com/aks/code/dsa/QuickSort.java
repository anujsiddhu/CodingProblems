package com.aks.code.dsa;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{
                3, 5, 1, 0, 0, 0, 0
        };
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int s, int e) {
        if (s >= e) {
            return;
        }

        int p = pivot(arr, s, e);
        quickSort(arr, s, p - 1);
        quickSort(arr, p + 1, e);
    }

    private static int pivot(int[] arr, int s, int e) {
        int p = s;
        int c = 0;

        for (int i = s + 1; i <= e; i++) {
            if (arr[i] < arr[p]) {
                c++;
            }
        }
        swap(arr, p, s + c);
        p = s + c;

        int i = s;
        int j = e;
        while (i < p && j > p) {
            while (i < p && arr[i] < arr[p]) {
                i++;
            }
            while (j > p && arr[j] > arr[p]) {
                j--;
            }

            if (i < p && j > p) {
                swap(arr, i, j);
            }
            i++;
            j--;
        }
        return p;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
