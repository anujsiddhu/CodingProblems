package com.aks.code.multithreading.thread;

// print series 010203040506.. 
// using multi-threading 1st thread will print only 0 
// 2nd thread will print only even numbers and 
// 3rd thread print only odd numbers

public class PrintSeries {
    static class ShareObject {
        public int i;
    }

    public static void main(String[] args) {
        ShareObject ob = new ShareObject();
        ob.i = -1;
        Thread t0 = new Thread(new PrintZero(ob));
        Thread t1 = new Thread(new Odd(ob));
        Thread t2 = new Thread(new Even(ob));
        t0.start();
        t1.start();
        t2.start();
    }

    static class PrintZero implements Runnable {
        private final ShareObject ob;

        public PrintZero(ShareObject ob) {
            this.ob = ob;
        }

        @Override
        public void run() {
            while (ob.i <= 10) {
                synchronized (ob) {
                    try {
                        while (ob.i > 0) {
                            ob.notify();
                            ob.wait();
                        }
                        System.out.println("Zero: " + 0);
                        ob.i = ob.i * -1;
                        ob.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }
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
                        ob.i = (ob.i + 1) * -1;
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
                        ob.i = (ob.i + 1) * -1;
                        ob.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}