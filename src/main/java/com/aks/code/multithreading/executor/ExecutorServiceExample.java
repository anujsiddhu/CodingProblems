package com.aks.code.multithreading.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        Random random = new Random();

        Set<Callable<Integer>> tasks = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            int waitTimeInSec = random.nextInt(5);
            Task t1 = new Task(i, waitTimeInSec);
            tasks.add(t1);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            List<Future<Integer>> result = executorService.invokeAll(tasks);
            for (Future<Integer> r : result) {
                System.out.println("result " + r.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // shut down executorService
        try {
            System.out.println("Trying to shut down executorService");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Task InterruptedException");
        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("Cancel non finish tasks");
            }
            executorService.shutdownNow();
            System.out.println("Shutdown finish");
        }

    }

    static class Task implements Callable<Integer> {
        int i;
        int waiTime;

        public Task(int i, int waiTime) {
            this.i = i;
            this.waiTime = waiTime;
        }

        @Override
        public Integer call() throws InterruptedException {
            Thread.sleep(1000L * waiTime);
            return i;
        }
    }
}
