package com.jonathan.service.item.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfiguration {

    @Bean("clientRest")
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }

    @Bean()
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> {
            return new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(
                            CircuitBreakerConfig.custom()
                                    .slidingWindowSize(10) //permite 10 peticiones.
                                    .failureRateThreshold(50) //fallas mayor al 50%
                                    .waitDurationInOpenState(Duration.ofSeconds(10L))
                                    .permittedNumberOfCallsInHalfOpenState(5)
                                    .slowCallRateThreshold(50) //porcentaje de llamadas lentas. Cuando el porcentaje es mayor que el umbral CircuitBreaker pasa a estado abierto.
                                    .slowCallDurationThreshold(Duration.ofSeconds(2L))
                                    .build()
                    ).timeLimiterConfig(TimeLimiterConfig
                            .custom()
                            .timeoutDuration(Duration.ofSeconds(3L)).build())
                    .build();
        });
    }

}