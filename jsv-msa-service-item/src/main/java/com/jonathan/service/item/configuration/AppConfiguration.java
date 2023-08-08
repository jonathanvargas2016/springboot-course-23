package com.jonathan.service.item.configuration;

import lombok.NoArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean("clientRest")
    @LoadBalanced
    public RestTemplate registerRestTemplate() {
        //LoadBalancerInterceptor loadBalancerInterceptor;
        //cliente para trabajar con http.
//        return new RestTemplateBuilder()
//                .interceptors(loadBalancerInterceptor)
//                .build();
    return new RestTemplate();
    }
}