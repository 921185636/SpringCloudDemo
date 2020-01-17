package com.list.customer2.customer2client.mq;

import com.list.customer2.customer2client.entity.LoginUser;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitConsumer {
    @RabbitListener(queues = "queue")
    @RabbitHandler
    public void process(String msg) {
        System.out.println("queue Receiver  : " + msg);
    }

    @RabbitListener(queues = "queue2")
    @RabbitHandler
    public void process2(String msg) {
        System.out.println("queue2 Receiver  : " + msg);
    }

    /*
    * 注解模式，不需要提前在配置类中进行配置
    * */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue3"),
            exchange = @Exchange(value = "LoginTopic", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "user.*"
    ))
    @RabbitHandler
    public void process3(@Payload LoginUser user, Channel channel, @Headers Map<String, Object> headers) {
        System.out.println("retry");
//        LoginUser user1 = new LoginUser();
//        user1.getUsername().equals(11);
        try{
            System.out.println("queue3 Receiver  : " + user.toString());
            long deliveryTag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
