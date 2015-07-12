package com.example.order;

public class Order {
    public final long id;
    public final String location;
    public final String name;
    public final int quantity;
    public final String milk;
    public final String size;
    public final OrderStatus status;

    public Order(long id,
                 String location,
                 String name,
                 int quantity,
                 String milk,
                 String size,
                 OrderStatus status) {

        this.id = id;
        this.location = location;
        this.name = name;
        this.quantity = quantity;
        this.milk = milk;
        this.size = size;
        this.status = status;
    }

    public Order changeQuantity(int quantity) {
        return new Order(id,
                        location,
                        name,
                        quantity,
                        milk,
                        size,
                        status);
    }

    enum OrderStatus {
        Pending,
        Ready,
        Served
    }
}

