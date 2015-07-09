package com.example.order;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class CreateOrderCommand {

    @Size(min = 2, max = 10, message = "Order name length must be between 2 and 10.")
    public final String name;

    @Min(value = 1, message = "Order quantity must be greater than or equal to 1.")
    public final int quantity;

    @NotEmpty(message = "Milk field is mandatory.")
    public final String milk;

    @NotEmpty(message = "Location field is mandatory.")
    public final String location;

    @NotEmpty(message = "Size field is mandatory.")
    public final String size;

    public CreateOrderCommand(CreateOrderDetail orderDetail) {
        name = orderDetail.name;
        location = orderDetail.location;
        quantity = Integer.parseInt(orderDetail.quantity);
        milk = orderDetail.milk;
        size = orderDetail.size;
    }
}
