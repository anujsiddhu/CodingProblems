package com.aks.code.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    public static void main(String[] args) {

        BlockingQueue<DelayedWorker> blockingQueue = new DelayQueue<>();

        try {
            blockingQueue.put(new DelayedWorker(1000, "This is message #1"));
            blockingQueue.put(new DelayedWorker(5000, "This is message #2"));
            blockingQueue.put(new DelayedWorker(2000, "This is message #3"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!blockingQueue.isEmpty()) {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class DelayedWorker implements Delayed {
        private long duration;
        private String message;

        public DelayedWorker(long duration, String message) {
            this.duration = System.currentTimeMillis() + duration;
            this.message = message;
        }

        @Override
        public int compareTo(Delayed otherDelayed) {
            return Long.compare(this.duration, ((DelayedWorker) otherDelayed).getDuration());
        }

        @Override
        public long getDelay(TimeUnit timeUnit) {
            return timeUnit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }
}

/**
 * This is an unbounded BlockingQueue of objects that implement the Delayed
 * interface
 * <p>
 * - DelayQueue keeps the elements internally until a certain delay has expired
 * <p>
 * - an object can only be taken from the queue when its delay has expired !!! -
 * <p>
 * We cannot place null items in the queue - The queue is sorted so that the
 * object at the head has a delay that has expired for the longest time.
 * <p>
 * If no delay has expired, then there is no head element and poll( ) will
 * return null
 * <p>
 * size() return the count of both expired and unexpired items !!!
 */