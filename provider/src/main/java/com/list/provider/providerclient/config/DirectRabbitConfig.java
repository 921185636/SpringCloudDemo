package com.list.provider.providerclient.config;

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

    @Bean(value = "producerRabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        rabbitTemplate.setMessageConverter(converter());

        // 消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息成功发送到Exchange");
                String msgId = correlationData.getId();
                //执行订单取消的操作
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }
        });
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });

        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean("test-consumer-connection-factory")
    public CachingConnectionFactory consumerCachingConnectionFactory() {
        return getCachingConnectionFactory();
    }
    @Bean
    @Primary
    public CachingConnectionFactory cachingConnectionFactory() {
        return getCachingConnectionFactory();
    }
    private CachingConnectionFactory getCachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();

        cachingConnectionFactory.setAddresses("114.55.66.218");
        cachingConnectionFactory.setUsername("root");
        cachingConnectionFactory.setPassword("root");
//        cachingConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
//        cachingConnectionFactory.addChannelListener(rabbitChannelListener);
//        cachingConnectionFactory.addConnectionListener(rabbitConnectionListener);
//        cachingConnectionFactory.setRecoveryListener(rabbitRecoveryListener);


        return cachingConnectionFactory;
    }
}
