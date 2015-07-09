package com.example.order;

import com.codahale.metrics.annotation.Timed;

import javax.validation.Validation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final String baseUrl;
    private AtomicLong counter;
    private final OrderRepository repository;

    public OrderResource(String baseUrl, OrderRepository repository) {
        this.baseUrl = baseUrl;
        this.counter = new AtomicLong();
        this.counter.getAndIncrement();
        this.repository = repository;
    }


    @POST
    @Timed
    public Response createOrder(CreateOrderDetail orderDetail) {
        Order order;
        try {
            CreateOrderCommand command = new CreateOrderCommand(orderDetail);

            List<String> validationErrors = Validation
                    .buildDefaultValidatorFactory()
                    .getValidator()
                    .validate(command)
                    .stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            if(!validationErrors.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(validationErrors)
                        .build();
            }

            long orderId = this.counter.getAndIncrement();
            order = new Order(orderId,
                    command.location,
                    command.name,
                    command.quantity,
                    command.milk,
                    command.size);

            repository.save(order);

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
            order = repository.get(id);
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
