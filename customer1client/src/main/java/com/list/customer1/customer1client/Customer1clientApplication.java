package com.list.customer1.customer1client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Customer1clientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Customer1clientApplication.class, args);
    }

}
