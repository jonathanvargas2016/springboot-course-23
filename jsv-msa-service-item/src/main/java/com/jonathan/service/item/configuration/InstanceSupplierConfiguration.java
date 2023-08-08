package com.jonathan.service.item.configuration;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

//https://www.baeldung.com/spring-cloud-load-balancer ---LEER LA DOCUMENTACION
public class InstanceSupplierConfiguration  implements ServiceInstanceListSupplier {

    @Override
    public String getServiceId() {
        return null;
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        return ServiceInstanceListSupplier.super.get(request);
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(
                Arrays.asList(new DefaultServiceInstance(
                         "instance1", "services-products", "localhost", 8080, false
                ), new DefaultServiceInstance(
                        "instance2", "services-products", "localhost", 9080, false
                ))
        );
    }
}
