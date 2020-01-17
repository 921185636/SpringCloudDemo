package com.list.customer2.customer2client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Slf4j
public class DirectRabbitConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public Queue queue() {
        return new Queue("queue");
//        return QueueBuilder.durable(“queue").build();
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue2");
    }

    /**
     * Fanout 交换机
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("fanout-exchange").build();
    }

    /**
     * 绑定队列queue到fanout交换机
     */
    @Bean
    Binding bindingQueueToFanoutExchange() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    /**
     * 绑定队列queue2 到fanout交换机
     */
    @Bean
    Binding bindingHello2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }


}
