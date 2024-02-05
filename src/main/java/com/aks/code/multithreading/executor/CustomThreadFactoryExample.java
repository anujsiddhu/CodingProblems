package com.aks.code.multithreading.executor;

import lombok.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CustomThreadFactoryExample {

    public static void main(String[] args) {
        CustomThreadFactory factory = new CustomThreadFactory();
        System.out.println("Starting the Threads");
        for (int i = 1; i <= 5; i++) {
            Thread thread = factory.newThread(new Task(i));
            thread.start();
        }
        System.out.println("All Threads are created now");
    }

    static class CustomThreadFactory implements ThreadFactory {
        private int counter = 1;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread t = new Thread(runnable, "Thread - " + counter);
            counter++;
            return t;
        }

    }

    static class Task implements Runnable {
        private final int id;
        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Running Task id " + id);
                TimeUnit.SECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
