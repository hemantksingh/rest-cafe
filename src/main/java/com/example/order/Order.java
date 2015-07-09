package com.example.order;

public class Order {
    public final long id;
    public final String location;
    public final String name;
    public final String quantity;
    public final String milk;
    public final String size;
    public final OrderStatus status;

    public Order(long id,
                 String location,
                 String name,
                 String quantity,
                 String milk,
                 String size) {

        this.id = id;
        this.location = location;
        this.name = name;
        this.quantity = quantity;
        this.milk = milk;
        this.size = size;
        this.status = OrderStatus.Pending;
    }

    enum OrderStatus {
        Pending,
        Ready,
        Served
    }
}

