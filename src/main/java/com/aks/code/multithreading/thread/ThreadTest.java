package com.aks.code.multithreading.thread;

public class ThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("Hello");
            System.out.println("Running");
        };

        Thread t = new Thread(r);
        t.start();
    }
}
