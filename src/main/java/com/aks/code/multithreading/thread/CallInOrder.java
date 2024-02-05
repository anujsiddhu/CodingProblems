package com.aks.code.multithreading.thread;

public class CallInOrder {

    public static void main(String[] args) throws InterruptedException {
        ShareObject ob = new ShareObject();
        ob.i = 1;
        Thread t1 = new Thread(new T1(ob));
        Thread t2 = new Thread(new T2(ob));
        Thread t3 = new Thread(new T3(ob));
        Thread.sleep(5000);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Main done");
    }

    static class T1 implements Runnable {
        private final ShareObject ob;

        public T1(ShareObject ob) {
            this.ob = ob;
        }

        @Override
        public void run() {
            synchronized (ob) {
                try {
                    while (ob.i != 1) {
                        ob.wait();
                    }
                    System.out.println("Call 1");
                    ob.i = 2;
                    ob.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    static class T2 implements Runnable {
        private final ShareObject ob;

        public T2(ShareObject ob) {
            this.ob = ob;
        }

        @Override
        public void run() {
            synchronized (ob) {
                try {
                    while (ob.i != 2) {
                        ob.wait();
                    }
                    System.out.println("Call 2");
                    ob.i = 3;
                    ob.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    static class T3 implements Runnable {
        private final ShareObject ob;

        public T3(ShareObject ob) {
            this.ob = ob;
        }

        @Override
        public void run() {
            synchronized (ob) {
                try {
                    while (ob.i != 3) {
                        ob.wait();
                    }
                    System.out.println("Call 3");
                    ob.i = 1;
                    ob.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    static class ShareObject {
        public int i;
    }
}