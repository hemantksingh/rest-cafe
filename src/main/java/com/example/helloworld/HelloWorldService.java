package com.example.helloworld;


import com.example.order.OrderResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by hkumar on 07/04/2015.
 */
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
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.healthChecks().register("template",
                new TemplateHealthCheck(configuration.getTemplate()));
        environment.jersey().register(helloWorldResource);
        environment.jersey().register(new DocumentResource());
        environment.jersey().register(new OrderResource());
    }
}
