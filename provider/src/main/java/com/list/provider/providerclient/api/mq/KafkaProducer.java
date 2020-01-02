package com.list.provider.providerclient.api.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/sendUser")
    public String sendMsg(@RequestParam("user") String user){
        String topic = "user";
        kafkaTemplate.send(topic,user);
        return "发送成功";
    }
}
