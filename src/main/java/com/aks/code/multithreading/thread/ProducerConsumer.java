package com.aks.code.multithreading.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Thread p = new Thread(new Producer(queue, 10));
        Thread c = new Thread(new Consumer(queue));
        c.start();
        p.start();
    }


    static class Producer implements Runnable {
        final Queue<Integer> queue;
        int size;

        public Producer(Queue<Integer> queue, int size) {
            this.queue = queue;
            this.size = size;
        }

        @Override
        public void run() {
            int i = 1;
            while (true) {
                synchronized (queue) {
                    try {
                        while (queue.size() >= 10) {
                            queue.wait();
                        }
                        System.out.println("Producer: Adding " + i);
                        queue.add(i);
                        queue.notify();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i > 100) {
                    break;
                }
                i++;
            }

        }
    }

    static class Consumer implements Runnable {
        Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    try {
                        while (queue.isEmpty()) {
                            queue.wait();
                        }
                        System.out.println("Consumer: Removing " + queue.poll());
                        queue.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}