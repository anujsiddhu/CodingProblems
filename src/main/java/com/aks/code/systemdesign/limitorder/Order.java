package com.aks.code.systemdesign.limitorder;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Order {
    private int id;
    private OrderType orderType;
    private int price;
    private int quantity;
    private LocalDateTime time;

    public Order(int id, OrderType orderType, int price, int quantity) {
        this.id = id;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
        this.time = LocalDateTime.now();
    }
}
