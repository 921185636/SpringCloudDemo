package com.list.customer2.customer2client.controller;

import com.list.customer2.customer2client.callable.SingleCallable;
import com.list.customer2.customer2client.entity.MyAnnotation;
import com.list.customer2.customer2client.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/consumer")
public class SingleController {
    @Autowired
    private SingleService singleService;
    @Resource(name = "threadPoolExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    //下面三个线程池点进去可以看到不同的线程池用了不同的缓存队列。
//    ExecutorService executorService1 = Executors.newFixedThreadPool(3);
//    ExecutorService executorService2 = Executors.newCachedThreadPool();
//    ExecutorService executorService3 = Executors.newSingleThreadExecutor();

    @MyAnnotation
    @RequestMapping("/discovery/server")
    public String getDiscoveryServers(@RequestParam(name = "a") String param){
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable callable = new SingleCallable(singleService);
            Future future = threadPoolTaskExecutor.submit(callable);
            futures.add(future);
        }
        String result = "";
        for (Future future : futures) {
            try {
                result += (String) future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
