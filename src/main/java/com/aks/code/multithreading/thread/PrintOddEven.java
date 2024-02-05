package com.aks.code.multithreading.thread;

public class PrintOddEven {
    static class ShareObject {
        public int i;
    }

    public static void main(String[] args) {
        ShareObject ob = new ShareObject();
        ob.i = 1;
        Thread t1 = new Thread(new Odd(ob));
        Thread t2 = new Thread(new Even(ob));
        t1.start();
        t2.start();
    }


    static class Odd implements Runnable {
        private final ShareObject ob;

        public Odd(ShareObject ob) {
            this.ob = ob;
        }

        @Override
        public void run() {
            while (ob.i <= 10) {
                synchronized (ob) {
                    try {
                        while (ob.i % 2 == 0) {
                            ob.wait();
                        }
                        System.out.println("Odd: " + ob.i);
                        ob.i = ob.i + 1;
                        ob.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }

    static class Even implements Runnable {
        private final ShareObject ob;

        public Even(ShareObject ob) {
            this.ob = ob;
        }

        @Override
        public void run() {
            while (ob.i <= 10) {
                synchronized (ob) {
                    try {
                        while (ob.i % 2 != 0) {
                            ob.wait();
                        }
                        System.out.println("Even " + ob.i);
                        ob.i = ob.i + 1;
                        ob.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
