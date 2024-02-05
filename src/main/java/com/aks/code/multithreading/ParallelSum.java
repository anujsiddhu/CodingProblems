package com.aks.code.multithreading;

import java.util.Random;

public class ParallelSum {
    private final ParallelWorker[] sums;
    private final int numOfThreads;

    public ParallelSum(int numOfThreads) {
        this.sums = new ParallelWorker[numOfThreads];
        this.numOfThreads = numOfThreads;
    }

    public int parallelSum(int[] nums) {
        int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            sums[i] = new ParallelWorker(nums, i * size, (i + 1) * size);
            sums[i].start();
        }

        try {
            for (ParallelWorker sum : sums) {
                sum.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (ParallelWorker sum : sums) {
            total += sum.getPartialSum();
        }

        return total;
    }

    static class ParallelWorker extends Thread {
        private final int[] nums;
        private final int low;
        private final int high;
        private int partialSum;

        public ParallelWorker(int[] nums, int low, int high) {
            this.nums = nums;
            this.low = low;
            this.high = Math.min(high, nums.length);
        }

        public int getPartialSum() {
            return partialSum;
        }

        @Override
        public void run() {
            partialSum = 0;
            for (int i = low; i < high; i++) {
                partialSum += nums[i];
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int numOfProcessors = Runtime.getRuntime().availableProcessors();
        int[] nums = new int[100000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(101) + 1; // 1..100
        }

        long start = System.currentTimeMillis();
        ParallelSum parallelSum = new ParallelSum(numOfProcessors);
        System.out.println("Sum is: " + parallelSum.parallelSum(nums));
        System.out.println("Parallel: " + (System.currentTimeMillis() - start) + "ms"); // Parallel: 25

    }
}
