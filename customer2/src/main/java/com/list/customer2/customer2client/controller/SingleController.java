package com.list.customer2.customer2client.controller;

import com.list.customer2.customer2client.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/consumer")
public class SingleController{
    @Autowired
    private SingleService singleService;
    ExecutorService executorService1 = Executors.newFixedThreadPool(3);
    ExecutorService executorService2 = Executors.newCachedThreadPool();
    ExecutorService executorService3 = Executors.newSingleThreadExecutor();
    @RequestMapping("/discovery/server")
    @ResponseBody
    public String getDiscoveryServers(){
        return singleService.getDiscoveryServers();
    }
}
