package com.aks.code.multithreading.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MaximumFindTask extends RecursiveTask<Long> {
    public static int THREASHOLD = 0;

    private final long[] nums;
    private final int lowIndex;
    private final int highIndex;

    public MaximumFindTask(long[] nums, int lowIndex, int highIndex) {
        this.highIndex = highIndex;
        this.lowIndex = lowIndex;
        this.nums = nums;
    }

    @Override
    protected Long compute() {

        if (highIndex - lowIndex < THREASHOLD) {
            return sequentialMaxFinding();
        } else {
            int middleIndex = (lowIndex + highIndex) / 2;
            MaximumFindTask leftSubtask = new MaximumFindTask(nums, lowIndex, middleIndex);
            MaximumFindTask rightSubtask = new MaximumFindTask(nums, middleIndex, highIndex);
            invokeAll(leftSubtask, rightSubtask);
            return Math.max(leftSubtask.join(), rightSubtask.join());
        }
    }

    public long sequentialMaxFinding() {
        long max = nums[0];
        for (int i = lowIndex; i < highIndex; ++i)
            if (nums[i] > max)
                max = nums[i];

        return max;
    }

    public static void main(String[] args) {
        long[] nums = initializeNums();
        THREASHOLD = nums.length / Runtime.getRuntime().availableProcessors();
        MaximumFindTask findTask = new MaximumFindTask(nums, 0, nums.length);

        long start = System.currentTimeMillis();
        System.out.println("Max: " + findTask.sequentialMaxFinding());
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        start = System.currentTimeMillis();
        System.out.println("Max: " + forkJoinPool.invoke(findTask));
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static long[] initializeNums() {
        Random random = new Random();
        long[] nums = new long[300000000];
        for (int i = 0; i < 300000000; ++i) {
            nums[i] = random.nextInt(100);
        }
        return nums;
    }
}
