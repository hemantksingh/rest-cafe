package com.example.helloworld;


import com.example.order.OrderRepository;
import com.example.order.OrderResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.filter.LoggingFilter;
import java.util.logging.Logger;

public class HelloWorldService extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // do nothing
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(
                configuration.template,
                configuration.defaultName
        );

        final Logger logger = Logger.getLogger(LoggingFilter.class.getName());
        environment.healthChecks().register("template",
                new TemplateHealthCheck(configuration.template));
        environment.jersey().register(new LoggingFilter(logger, true));
        environment.jersey().register(helloWorldResource);
        environment.jersey().register(new OrderResource(configuration.baseUrl, new OrderRepository()));
    }
}
