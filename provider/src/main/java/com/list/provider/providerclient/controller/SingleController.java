package com.list.provider.providerclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/provider")
public class SingleController {
    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String providerName;

    @GetMapping("/discovery")
    @ResponseBody
    public String discoveryServers() {
        System.out.println("访问到provider啦");
        return "provider返回的信息: " + providerName + ":" + port;
    }
}
