package com.aks.code.multithreading.executor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPoolExample {

    public static void main(String[] args) {
        CustomThreadPool customThreadPool = new CustomThreadPool(2);
        for (int i = 1; i <= 10; i++) {
            customThreadPool.execute(new Task("Task " + i));
        }

        while (!customThreadPool.isCompleted()) {
        }
        customThreadPool.shutdown();
    }

    static class CustomThreadPool {
        private final int poolSize;
        private final WorkerThread[] workers;
        private final LinkedBlockingQueue<Runnable> queue;

        public CustomThreadPool(int poolSize) {
            this.poolSize = poolSize;
            this.queue = new LinkedBlockingQueue<>();
            this.workers = new WorkerThread[poolSize];

            for (int i = 0; i < poolSize; i++) {
                workers[i] = new WorkerThread(i, queue);
                workers[i].start();
            }
        }

        public void execute(Runnable task) {
            synchronized (queue) {
                queue.add(task);
                queue.notifyAll();
            }
        }

        public boolean isCompleted() {
            return queue.isEmpty()
                    && Arrays.stream(workers).filter(Objects::nonNull).noneMatch(w -> w.assignedTask != null);
        }

        public void shutdown() {
            System.out.println("Shutting down thread pool");
            for (int i = 0; i < poolSize; i++) {
                workers[i].stop();
                workers[i] = null;
            }
        }
    }

    static class WorkerThread extends Thread {
        private final int workerId;
        private final LinkedBlockingQueue<Runnable> queue;

        private Runnable assignedTask;
        public WorkerThread(int workerId, LinkedBlockingQueue<Runnable> queue) {
            this.workerId = workerId;
            this.queue = queue;
            this.assignedTask = null;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Worker " + workerId + " Interrupted");
                        }
                    }
                    assignedTask = queue.poll();
                }
                try {
                    System.out.println(assignedTask + " is running by workerId " + workerId);
                    assignedTask.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
                assignedTask = null;
            }
        }
    }

    static class Task implements Runnable {
        private final String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                System.out.println(this + " InterruptedException: " + e.getMessage());
            }
            System.out.println(this + " executed on : " + LocalDateTime.now());
        }

        @Override
        public String toString() {
            return "Task " + name;
        }
    }
}
