package com.aks.code.multithreading.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorMyBlockingQueue {

    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(50);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue);
        MyRejectionHandler rejectedExecutionHandler = new MyRejectionHandler();
        threadPool.setRejectedExecutionHandler(rejectedExecutionHandler);
        queue.tpe(threadPool);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Task(i, queue));
        }
    }

    static class Task implements Runnable {
        private final int taskId;
        private final MyBlockingQueue queue;

        public Task(int id, MyBlockingQueue queue) {
            this.taskId = id;
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println(this + " performed by " + Thread.currentThread().getName()  + " Queue Size: " + queue.size());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task taskId= " + taskId + " ";
        }
    }

    static class MyBlockingQueue extends LinkedBlockingQueue<Runnable> {
        private static final long serialVersionUID = 1L;
        private ThreadPoolExecutor tpe;

        public MyBlockingQueue(int capacity) {
            super(capacity);
        }

        @Override
        public boolean offer(Runnable e) {
            int poolSize = tpe.getPoolSize();
            int maximumPoolSize = tpe.getMaximumPoolSize();
            if (poolSize >= maximumPoolSize || poolSize > tpe.getActiveCount()) {
                return super.offer(e);
            } else {
                return false;
            }
        }

        public void tpe(ThreadPoolExecutor tpe) {
            this.tpe = tpe;
        }
    }

    static class MyRejectionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                boolean added = executor.getQueue().offer(r);
                System.out.println("Task " + r + " Added: " + added + " queue size " + executor.getQueue().size());
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

}