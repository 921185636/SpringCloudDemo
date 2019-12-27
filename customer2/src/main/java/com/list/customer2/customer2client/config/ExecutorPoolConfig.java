package com.list.customer2.customer2client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
* 借鉴：
* https://blog.51cto.com/14230003/2418026?source=dra
* https://www.cnblogs.com/xiguadadage/p/10243332.html
* https://blog.csdn.net/weixin_43168010/article/details/97613895
* */

@Configuration
@EnableAsync
public class ExecutorPoolConfig {
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    @Bean(name = "threadPoolExecutor")
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);//核心池大小
        executor.setMaxPoolSize(100);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("task-form-pool");//线程前缀名称
      /*
      * AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
      * CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
      * DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
      * DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
      * */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//拒绝策略
        executor.setWaitForTasksToCompleteOnShutdown(true);//调度器shutdown被调用时等待当前被调度的任务完成
        executor.setAwaitTerminationSeconds(60); //等待时长
        executor.initialize();//执行初始化
        return executor;
    }
    @Bean
    public ScheduledThreadPoolExecutor asyncScheduledThreadPoolExecutor() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.setMaximumPoolSize(2);
        executor.setKeepAliveTime(5, TimeUnit.SECONDS);
        return executor;
    }
}
