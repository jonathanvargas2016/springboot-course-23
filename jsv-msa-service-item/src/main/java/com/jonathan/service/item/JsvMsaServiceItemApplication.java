package com.jonathan.service.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //habilitar clientes feign ademas permite inyectar estos clientes en los controllers u otros clientes spring
@SpringBootApplication
public class JsvMsaServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsvMsaServiceItemApplication.class, args);
	}

}
