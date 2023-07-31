package com.jonathan.service.item.configuration;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@NoArgsConstructor
public class AppConfiguration {

    @Bean("clientRest")
    public RestTemplate registerRestTemplate(){
        //cliente para trabajar con http.
        return new RestTemplate();
    }

}