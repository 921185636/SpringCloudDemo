package com.list.customer3.customer3zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Customer3zuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Customer3zuulApplication.class, args);
    }

}
