package com.jonathan.zuul.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class ExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<ExampleGatewayFilterFactory.Configuration> {
    private final Logger logger = LoggerFactory.getLogger(ExampleGatewayFilterFactory.class);

    public ExampleGatewayFilterFactory() {
        super(Configuration.class);
    }

    @Override
    public GatewayFilter apply(String routeId, Consumer<Configuration> consumer) {
        return super.apply(routeId, consumer);
    }

    @Override
    public GatewayFilter apply(Consumer<Configuration> consumer) {
        return super.apply(consumer);
    }

    @Override
    public GatewayFilter apply(Configuration config) {

        //pre
        return ((exchange, chain) -> {
            logger.info("Executing pre gateway filter factory" + config.message);

            //post. similar el metodo then()
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                Optional.ofNullable(config.cookieValue).ifPresent(cookie -> {
                    exchange.getResponse()
                            .addCookie(ResponseCookie.from(config.cookieName, cookie).build());
                });

                logger.info("Executing post gateway filter factory" + config.message);

            }));
        });
    }

    @Override
    public GatewayFilter apply(String routeId, Configuration config) {
        return super.apply(routeId, config);
    }

    @Override
    public String name() {
        return "ExampleCookie";
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("message", "cookieName", "cookieValue");
    }




    public static class Configuration {
        private String message;
        private String cookieValue;
        private String cookieName;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }
    }
}

