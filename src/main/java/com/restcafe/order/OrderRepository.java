package com.restcafe.order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(String.valueOf(order.id), order);
    }

    public Order get(String id) {
        return orders.get(id);
    }
}
