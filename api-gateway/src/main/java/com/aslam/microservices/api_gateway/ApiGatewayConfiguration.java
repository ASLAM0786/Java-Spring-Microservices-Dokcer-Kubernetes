package com.aslam.microservices.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator getewayRouter(RouteLocatorBuilder builder) {
//        Function<PredicateSpec, Buildable<Route>> routeFuction
//                = p -> p.path("/get")
//                .filters(f -> f.addRequestHeader("myHeader", "myURI"))
//                .uri("http://httpbin.org:80");
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("myHeader", "myURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}