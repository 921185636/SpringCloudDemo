package com.list.customer2.customer2client.mq;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"user"})
    public void getMsg(String msg){
        System.out.println("消费者受到消息: " + msg);
    }
}
