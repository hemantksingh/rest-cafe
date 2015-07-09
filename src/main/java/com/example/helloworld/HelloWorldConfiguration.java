package com.example.helloworld;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hkumar on 07/04/2015.
 */
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    public String defaultName = "Anonymous";

    @NotEmpty
    public String template;

    @NotEmpty
    public String baseUrl;

}
