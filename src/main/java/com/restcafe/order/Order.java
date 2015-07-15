package com.restcafe.order;

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
        if(this.status != OrderStatus.Pending)
            throw new UnsupportedOperationException("Order already made or served.");
        return new Order(id,
                        location,
                        name,
                        quantity,
                        milk,
                        size,
                        status);
    }

    public Order make() {
        if(this.status == OrderStatus.Served)
            throw new UnsupportedOperationException("Order has already been served.");

        return new Order(id,
                location,
                name,
                quantity,
                milk,
                size,
                OrderStatus.Ready);
    }

    public Order serve() {
        if(this.status != OrderStatus.Ready)
            throw new UnsupportedOperationException("Order not yet ready to serve.");

        return new Order(id,
                location,
                name,
                quantity,
                milk,
                size,
                OrderStatus.Served);
    }

    enum OrderStatus {
        Pending,
        Ready,
        Served
    }
}

