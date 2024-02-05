package com.aks.code.multithreading.thread;

public class FizzBuzzThreads {

    public static void main(String[] args) {
        ShareObject ob = new ShareObject();
        ob.i = 1;
        int max = 100;
        Thread t1 = new Thread(new GenerateText(ob, max, "FB", true, true));
        Thread t2 = new Thread(new GenerateText(ob, max, "F", false, true));
        Thread t3 = new Thread(new GenerateText(ob, max, "B", true, false));
        Thread t4 = new Thread(new GenerateText(ob, max, "", false, false));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    static class GenerateText implements Runnable {
        ShareObject ob;
        int max;
        String p;
        boolean d3;
        boolean d5;

        public GenerateText(ShareObject ob, int max, String p, boolean d3, boolean d5) {
            this.ob = ob;
            this.max = max;
            this.p = p;
            this.d3 = d3;
            this.d5 = d5;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (ob) {
                        if (ob.i > max) {
                            return;
                        }
                        if (((ob.i % 3 == 0) == d3) && ((ob.i % 5 == 0) == d5)) {
                            System.out.println(ob.i + " " + p);
                            ob.i = ob.i + 1;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ShareObject {
        public int i;
    }
}