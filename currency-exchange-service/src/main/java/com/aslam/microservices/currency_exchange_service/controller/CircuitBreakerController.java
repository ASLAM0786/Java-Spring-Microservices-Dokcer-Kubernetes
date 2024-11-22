package com.aslam.microservices.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodeResponse")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodeResponse")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String sampleApi() {
        logger.info("Sample API called");
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-api", String.class);
//        return forEntity.getBody();
        return "Sample API";
    }

    public String hardcodeResponse(Exception exception) {
        return "fallback-response";
    }
}
