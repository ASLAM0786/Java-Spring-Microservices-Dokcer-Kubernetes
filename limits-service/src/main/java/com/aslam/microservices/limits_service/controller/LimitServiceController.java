package com.aslam.microservices.limits_service.controller;

import com.aslam.microservices.limits_service.LimitConfiguration;
import com.aslam.microservices.limits_service.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitServiceController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitFromConfiguration() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
        //return new LimitConfiguration(1000, 1);
    }
}
