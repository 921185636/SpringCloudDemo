package com.list.provider.providerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderclientApplication.class, args);
    }

}
