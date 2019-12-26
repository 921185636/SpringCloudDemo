package com.list.customer2.customer2client.service;

import org.springframework.stereotype.Component;

@Component
public class SingleServiceHystric implements SingleService{
    @Override
    public String getDiscoveryServers() {
        return "sorry,error";
    }
}
