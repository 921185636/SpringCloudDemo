package com.list.customer2.customer2client.controller;

import com.list.customer2.customer2client.callable.SingleCallable;
import com.list.customer2.customer2client.entity.MyAnnotation;
import com.list.customer2.customer2client.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/consumer")
public class SingleController{
    @Resource(name = "threadPoolExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private SingleService singleService;

    //点进去看看可以知道不同的线程池是使用了什么队列
//    ExecutorService executorService1 = Executors.newFixedThreadPool(3);
//    ExecutorService executorService2 = Executors.newCachedThreadPool();
//    ExecutorService executorService3 = Executors.newSingleThreadExecutor();

    @RequestMapping("/discovery/server")
    @ResponseBody
    public String getDiscoveryServers() throws ExecutionException, InterruptedException {
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable callable = new SingleCallable(singleService);
            Future submit = executor.submit(callable);
            futures.add(submit);
        }

        StringBuilder result = new StringBuilder();
        for (Future future : futures) {
            result.append((String) future.get());
        }
        return result.toString();
    }
    @MyAnnotation
    @RequestMapping("testAop")
    public String testaop(String a){
        return a+"1";
    }
}
