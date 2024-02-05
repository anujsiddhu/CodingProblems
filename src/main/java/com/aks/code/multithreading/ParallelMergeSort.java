package com.aks.code.multithreading;

import java.util.Arrays;
import java.util.Random;

public class ParallelMergeSort {
    public static Random random = new Random();
    public static void main(String[] args) throws Throwable {

        int numOfThreads  = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads/cores: " + numOfThreads);

        int[] numbers = createRandomArray();
        ParallelMergeSort mergeSort = new ParallelMergeSort();

        long startTime1 = System.currentTimeMillis();
        mergeSort.parallelMergeSort(numbers, numOfThreads);
        long endTime1 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(numbers));
        System.out.printf("Time taken for 100 000 000 elements parallel =>  %6d ms \n", endTime1 - startTime1);


        numbers = createRandomArray();
        startTime1 = System.currentTimeMillis();
        mergeSort.mergeSort(numbers);
        endTime1 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(numbers));
        System.out.printf("Time taken for 100 000 000 elements sequential =>  %6d ms \n", endTime1 - startTime1);

    }

    public static int[] createRandomArray() {
        int[] a = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            a[i] = random.nextInt(10000);
        }
        return a;
    }
    public void mergeSort(int[] a) {
        if (a.length <= 1)
            return;

        int mid = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);

        mergeSort(left);
        mergeSort(right);
        merge(left, right, a);
    }
    public void parallelMergeSort(int[] numbers, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(numbers);
            return;
        }

        int middleIndex = numbers.length / 2;
        int[] leftSubarray = Arrays.copyOfRange(numbers, 0, middleIndex);
        int[] rightSubarray = Arrays.copyOfRange(numbers, middleIndex, numbers.length);

        Thread leftSorter = mergeSortThread(leftSubarray, numOfThreads);
        Thread rightSorter = mergeSortThread(rightSubarray, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(leftSubarray, rightSubarray, numbers);
    }

    private Thread mergeSortThread(int[] numbers, int numOfThreads) {
        return new Thread(() -> parallelMergeSort(numbers, numOfThreads / 2));
    }

    private void merge(int[] leftSubarray, int[] rightSubarray, int[] originalArray) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSubarray.length && j < rightSubarray.length) {
            if (leftSubarray[i] < rightSubarray[j])
                originalArray[k++] = leftSubarray[i++];
            else
                originalArray[k++] = rightSubarray[j++];
        }

        while (i < leftSubarray.length)
            originalArray[k++] = leftSubarray[i++];

        while (j < rightSubarray.length)
            originalArray[k++] = rightSubarray[j++];
    }
}
