package com.aks.code.systemdesign.limitorder;

import java.util.concurrent.PriorityBlockingQueue;

public class LimitOrderProcessor implements OrderBook {
    private final PriorityBlockingQueue<Order> buyQueue;
    private final PriorityBlockingQueue<Order> sellQueue;

    public LimitOrderProcessor() {
        buyQueue = new PriorityBlockingQueue<>(100, new BuyOrderComparator());
        sellQueue = new PriorityBlockingQueue<>(100, new SellOrderComparator());
    }

    @Override
    public void execute(Order order) {
        if (order.getOrderType() == OrderType.BUY) {
            buyQueue.add(order);
        } else {
            sellQueue.add(order);
        }
    }

    public void process() {
        new Thread(() -> {
            while (true) {
                System.out.println("=============================================================================================");
                System.out.println("BUY Orders");
                buyQueue.forEach(System.out::println);
                System.out.println("=============================================================================================");
                System.out.println("SELL Orders");
                sellQueue.forEach(System.out::println);
                System.out.println("=============================================================================================");
                try {
                    Order buy = buyQueue.peek();
                    Order sell = sellQueue.peek();
                    if (buy != null && sell != null) {
                        if (buy.getPrice() == sell.getPrice()) {
                            if (buy.getQuantity() == sell.getQuantity()) {
                                buy = buyQueue.take();
                                sell = sellQueue.take();
                                System.out.println("Full Order processed: " + buy + " and " + sell);
                            } else if (buy.getQuantity() > sell.getQuantity()) {
                                sell = sellQueue.take();
                                System.out.println("Order processed: " + buy + " with " + sell.getQuantity() + " full and " + sell);
                                buy.setQuantity(buy.getQuantity() - sell.getQuantity());
                            } else {
                                buy = buyQueue.take();
                                System.out.println("Order processed: full " + buy + " and " + sell + " with " + buy.getQuantity());
                                sell.setQuantity(sell.getQuantity() - buy.getQuantity());
                            }
                        }
                    }
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }


}
