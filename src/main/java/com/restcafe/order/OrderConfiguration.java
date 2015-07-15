package com.restcafe.order;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class OrderConfiguration extends Configuration {

    @NotEmpty
    public String baseUrl;

}
