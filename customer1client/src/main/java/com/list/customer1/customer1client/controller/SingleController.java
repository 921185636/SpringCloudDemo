package com.list.customer1.customer1client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class SingleController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVER_PROVIDER = "http://list-provider-1";

    @RequestMapping("/discovery/server")
    @ResponseBody
    public String getDiscoveryServers(){
        String forObject = restTemplate.getForObject(SERVER_PROVIDER + "/provider/discovery", String.class);
        return forObject;
    }
}
