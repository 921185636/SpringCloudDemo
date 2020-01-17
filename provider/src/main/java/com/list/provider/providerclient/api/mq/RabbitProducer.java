package com.list.provider.providerclient.api.mq;

import com.list.provider.providerclient.entity.LoginUser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;


@RestController
public class RabbitProducer {
    @Resource(name = "producerRabbitTemplate")
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        //只发给单一队列
        rabbitTemplate.convertAndSend("queue", " send msg to queue By producer");
        return "direct ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        //fanout 模式 ，消息发给交换机，通过交换机发给所有队列
        rabbitTemplate.convertAndSend("fanout-exchange", "", "（fanout）发送到交换机的消息");
        return "fanout ok";
    }


    @GetMapping("/sendUserTopicMessage")
    public String sendUserTopicMessage() {
        LoginUser user = new LoginUser();
        user.setUsername("tiger");
        user.setPassword("12345688");
        String routingKey = "user." + user.getUsername();
        //topic 模式 ，消息发给交换机，通过交换机发给与key绑定的队列
        rabbitTemplate.convertAndSend("LoginTopic", routingKey, user);

        //通过将messageID设置为随机，解决幂等性
//        Message msg = MessageBuilder.withBody("xxx".getBytes())
//        .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
//        .setMessageId(UUID.randomUUID() + "").build();
//        rabbitTemplate.convertAndSend("xxx",msg);

        return "topic ok";
    }

    @GetMapping("/sendToErrorExchange")
    public String sendToErrorExchange() {
        LoginUser user = new LoginUser();
        user.setUsername("tiger");
        user.setPassword("12345688");
        String routingKey = "user." + user.getUsername();
        rabbitTemplate.convertAndSend("error", routingKey, user);
        return "Exchange error";
    }

    @GetMapping("/ExchangeToQueueError")
    public String ExchangeToQueueError() {
        LoginUser user = new LoginUser();
        user.setUsername("tiger");
        user.setPassword("12345688");
        String routingKey = "direct.error";
        //topic 模式 ，消息发给交换机，通过交换机发给与key绑定的队列
        rabbitTemplate.convertAndSend("LoginTopic", routingKey, user);
        return "ExchangeToQueueError";
    }

}
