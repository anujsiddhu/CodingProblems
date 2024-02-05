package com.aks.code.systemdesign.limitorder;

import java.util.Comparator;

public class SellOrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getPrice() == o2.getId()) {
            return o1.getTime().compareTo(o2.getTime());
        } else {
            return o1.getPrice() - o2.getId();
        }
    }
}
