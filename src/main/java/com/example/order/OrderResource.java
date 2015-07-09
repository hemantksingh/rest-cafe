package com.example.order;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private AtomicLong counter;
    private ImmutableMap<String, Order> orders;

    public OrderResource() {
        this.counter = new AtomicLong();
        this.counter.getAndIncrement();
    }


    @POST
    @Timed
    public Response createOrder(OrderDetail orderDetail) {
        long orderId = this.counter.getAndIncrement();

        Order order = new Order(orderId,
                orderDetail.location,
                orderDetail.name,
                orderDetail.quantity,
                orderDetail.milk,
                orderDetail.size);

        orders = ImmutableMap.<String, Order>builder()
                .put(String.valueOf(orderId), order)
                .build();


        Response response = Response.status(201)
                .entity(order)
                .header("Location",
                        String.format("localhost:8080/order/%d", orderId))
                .build();

        return response;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Response getOrder(@PathParam("id") String id) {
        Order order = orders.get(id);

        if(order == null) {
            return Response.status(404)
                    .build();
        }

        return Response.status(200)
                .entity(order)
                .build();
    }
}
