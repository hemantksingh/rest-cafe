package com.restcafe.order;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.filter.LoggingFilter;
import java.util.logging.Logger;

public class OrderService extends Application<OrderConfiguration> {

    public static void main(String[] args) throws Exception {
        new OrderService().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<OrderConfiguration> bootstrap) {
        // do nothing
    }

    @Override
    public void run(OrderConfiguration configuration, Environment environment) throws Exception {

        final Logger logger = Logger.getLogger(LoggingFilter.class.getName());
        environment.healthChecks().register("baseUrl",
                new BaseUrlHealthCheck(configuration.baseUrl));
        environment.jersey().register(new LoggingFilter(logger, true));
        environment.jersey().register(new OrderResource(configuration.baseUrl, new OrderRepository()));
    }
}
