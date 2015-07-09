package com.example.order;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final String baseUrl;
    private AtomicLong counter;
    private Map<String, Order> orders;

    public OrderResource(String baseUrl) {
        this.baseUrl = baseUrl;
        this.counter = new AtomicLong();
        this.counter.getAndIncrement();
        this.orders = new HashMap<>();
    }


    @POST
    @Timed
    public Response createOrder(CreateOrderDetail orderDetail) {
        Order order;
        try {
            long orderId = this.counter.getAndIncrement();

            order = new Order(orderId,
                    orderDetail.location,
                    orderDetail.name,
                    orderDetail.quantity,
                    orderDetail.milk,
                    orderDetail.size);

            orders.put(String.valueOf(orderId), order);

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error occurred while processing your request.")
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity(order)
                .header("Location",
                        String.format("http://%s/order/%d", baseUrl, order.id))
                .build();
    }

    @GET
    @Path("/{id}")
    @Consumes("*/*")
    @Timed
    public Response getOrder(@PathParam("id") String id) {
        Order order;
        try {
            order = orders.get(id);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error occurred while processing your request.")
                    .build();
        }

        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("No order found for id - '%s' ", id))
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity(order)
                .build();
    }
}
