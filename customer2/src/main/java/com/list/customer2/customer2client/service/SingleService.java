package com.list.customer2.customer2client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "list-provider-1",fallback = SingleServiceHystric.class)
public interface SingleService {
    @RequestMapping("/provider/discovery")
    String getDiscoveryServers();
}
