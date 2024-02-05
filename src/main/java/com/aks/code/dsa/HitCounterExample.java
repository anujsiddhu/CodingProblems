package com.aks.code.dsa;

import java.util.LinkedList;
import java.util.Queue;

public class HitCounterExample {
    public static void main(String[] args) {
        HitCounter hc = new HitCounter();
        hc.hit(1);
        hc.hit(2);
        hc.hit(3);
        System.out.println(hc.getHits(4));

        hc.hit(300);
        hc.hit(300);
        System.out.println(hc.getHits(300));
        hc.hit(301);
        System.out.println(hc.getHits(301));

    }

    static class HitCounter {
        private final Queue<Integer> queue;
        private final int window = 300;

        public HitCounter() {
            queue = new LinkedList<>();
        }

        public void hit(int timestamp) {
            queue.add(timestamp);
        }

        public int getHits(int timestamp) {
            while (!queue.isEmpty() && queue.peek() <= timestamp - window) {
                queue.poll();
            }
            return queue.size();
        }
    }
}
