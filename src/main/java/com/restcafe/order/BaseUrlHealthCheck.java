package com.restcafe.order;

import com.codahale.metrics.health.HealthCheck;

public class BaseUrlHealthCheck extends HealthCheck {

    private final String baseUrl;

    public BaseUrlHealthCheck(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Override
    protected Result check() throws Exception {
        if(!baseUrl.isEmpty()){
            return HealthCheck.Result.unhealthy("baseUrl is not defined");
        }

        return Result.healthy(baseUrl);
    }
}
