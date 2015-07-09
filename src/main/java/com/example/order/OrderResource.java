package com.example.order;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private AtomicLong counter;

    public OrderResource() {
        this.counter = new AtomicLong();
    }


    @POST
    @Timed
    public Response createOrder(OrderDetail orderDetail) {
        long orderId = this.counter.getAndIncrement();

        ImmutableMap<String, String> order = ImmutableMap.<String, String>builder()
                .put("orderId", String.valueOf(orderId))
                .put("location", orderDetail.location)
                .put("name", orderDetail.name)
                .put("quantity", orderDetail.quantity)
                .put("milk", orderDetail.milk)
                .put("size", orderDetail.size)
                .build();

        Response response = Response.status(201)
                .entity(order)
                .header("Location",
                        String.format("localhost:8080/order/%d", orderId))
                .build();

        return response;
    }
}
