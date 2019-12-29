package com.list.customer2.customer2client.callable;

import com.list.customer2.customer2client.service.SingleService;

import java.util.concurrent.Callable;

public class SingleCallable implements Callable {
    private SingleService singleService;
    public SingleCallable(SingleService singleService) {
        this.singleService = singleService;
    }

    @Override
    public Object call() throws Exception {
        String discoveryServers = singleService.getDiscoveryServers();
        return discoveryServers;
    }
}
