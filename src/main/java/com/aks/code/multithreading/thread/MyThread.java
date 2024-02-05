package com.aks.code.multithreading.thread;

import java.math.BigInteger;

public class MyThread implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyThread());
        myThread.setUncaughtExceptionHandler((t, e) -> System.out.println("exception handled " + e.getMessage()));
        myThread.start();

        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));

        thread.start();
        thread.join();
        thread.interrupt();

    }

    @Override
    public void run() {
        System.out.println("running");
        throw new RuntimeException("MyEXP");
    }

    private static class LongComputationTask implements Runnable {
        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
