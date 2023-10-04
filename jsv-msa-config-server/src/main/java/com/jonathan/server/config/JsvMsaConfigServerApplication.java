package com.jonathan.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class JsvMsaConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsvMsaConfigServerApplication.class, args);
    }

}
