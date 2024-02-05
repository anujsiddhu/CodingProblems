package com.aks.code.multithreading.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciNumberTask extends RecursiveTask<Integer> {
    private final int n;

    public FibonacciNumberTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FibonacciNumberTask fib1 = new FibonacciNumberTask(n - 1);
        FibonacciNumberTask fib2 = new FibonacciNumberTask(n - 2);
        fib1.fork();
        fib2.fork();

        return fib1.join() + fib2.join();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(5);
        System.out.println(pool.invoke(new FibonacciNumberTask(45)));
        long end = System.currentTimeMillis();
        System.out.println("total time: " + (end - start)/1000);
    }
}
