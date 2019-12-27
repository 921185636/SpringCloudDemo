package com.list.customer2.customer2client.callable;

import com.list.customer2.customer2client.service.SingleService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class SingleCallable implements Callable {
    private SingleService singleService;

    public SingleCallable(SingleService singleService) {
        this.singleService = singleService;
    }

    @Override
    public String call() {
        log.info("线程：{},",  Thread.currentThread().getName());
        return singleService.getDiscoveryServers();
    }
}
