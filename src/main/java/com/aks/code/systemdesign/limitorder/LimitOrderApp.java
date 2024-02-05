package com.aks.code.systemdesign.limitorder;

import java.util.Random;

public class LimitOrderApp {
    public static void main(String[] args) {
        LimitOrderProcessor limitOrderProcessor = new LimitOrderProcessor();
        ConcurrentProducer producer = new ConcurrentProducer(limitOrderProcessor);
        producer.start();
        limitOrderProcessor.process();
    }

    private static class ConcurrentProducer extends Thread {

        private final LimitOrderProcessor limitOrderProcessor;

        Random random = new Random();

        public ConcurrentProducer(LimitOrderProcessor limitOrderProcessor) {
            this.limitOrderProcessor = limitOrderProcessor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    int randomId1 = random.nextInt(100000) + 1;
                    int randomId2 = random.nextInt(100000) + 1;
                    int quantity1 = random.nextInt(10) + 1;
                    int quantity2 = random.nextInt(10) + 1;
                    int price = random.nextInt(100) + 1;
                    Order buy = new Order(randomId1, OrderType.BUY, price, quantity1);
                    Order sell = new Order(randomId2, OrderType.SELL, price, quantity2);
//                System.out.println("Producer adding order: " + buy);
//                System.out.println("Producer adding order: " + sell);
                    limitOrderProcessor.execute(buy);
                    limitOrderProcessor.execute(sell);
                    Thread.sleep(2000);
                } catch (Exception e) { //(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}