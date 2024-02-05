package com.aks.code.multithreading.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// https://crunchify.com/java-thread-futures-timedout-cancelled-interrupted/
public class ExecutorServiceTimeoutCancel {

    public static void main(final String[] args) {
        CompletionService<Integer> executorService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(1));
        List<TaskRunner> threads = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            threads.add(new TaskRunner(i));
        }

        List<Future<Integer>> futures = new ArrayList<>();
        for (Callable<Integer> task : threads) {
            futures.add(executorService.submit(task));
        }

        try {
            for (int count = 1; count <= 200; count++) {
                Future<Integer> result = executorService.poll(1000, TimeUnit.MILLISECONDS);
                if (result == null) {
                    // So lets cancel the first futures we find that haven't completed
                    for (Future<Integer> future : futures) {
                        if(!future.isDone()) {
                            future.cancel(true);
                            break;
                        }
                    }
                } else {
                    if (result.isDone() && !result.isCancelled() && result.get() != null && result.get() > 0) {
                        System.out.println("Worker task " + result.get() + " completed.");
                    }
                }
            }
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        } finally {
            // Cancel by interrupting any existing tasks currently running in Executor Service
            for (Future<Integer> future : futures) {
                future.cancel(true);
            }
        }
        System.out.println("Done!!");
    }

    static class TaskRunner implements Callable<Integer> {
        private int workerNumber;

        public int getNumber() {
            return workerNumber;
        }

        public void setNumber(int workerNumber) {
            this.workerNumber = workerNumber;
        }

        public TaskRunner(int workerNumber) {
            this.workerNumber = workerNumber;
            setNumber(workerNumber);
        }

        @Override
        public Integer call() {
            try {
                if (workerNumber % 2 == 0) {
                    Thread.sleep(20000);
                } else {
                    Thread.sleep(50);
                }
            } catch (InterruptedException ie) {
                System.out.println("Worker task " + workerNumber + " interrupted.");
            }
            return getNumber();
        }
    }
}
