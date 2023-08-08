package com.jonathan.service.item.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean("clientRest")
    @LoadBalanced
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }


}