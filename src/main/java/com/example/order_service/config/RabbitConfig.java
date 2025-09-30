package com.example.order_service.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String ORDER_QUEUE = "order.queue";
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_ROUTING_KEY = "order.created";
    public static final String ORDER_DLX = "order.dlx";
    public static final String ORDER_DLQ_ROUTING_KEY = "order.dlq";
    @Bean
    public Queue orderQueue(){
        return QueueBuilder.durable(ORDER_QUEUE)
                .deadLetterExchange(ORDER_DLX)
                .deadLetterRoutingKey(ORDER_DLQ_ROUTING_KEY)
                .build();
    }
    @Bean
    public Queue orderDLQ() {
        return QueueBuilder.durable(ORDER_DLQ_ROUTING_KEY).build();
    }
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(ORDER_DLX);
    }
    @Bean
    public Binding dlqBinding(Queue orderDLQ, DirectExchange dlxExchange) {
        return BindingBuilder.bind(orderDLQ)
                .to(dlxExchange)
                .with(ORDER_DLQ_ROUTING_KEY);
    }

    @Bean
    public TopicExchange orderExchange(){
        return new TopicExchange(ORDER_EXCHANGE , true , false);
    }
    @Bean
    public Binding binding(Queue orderQueue, TopicExchange orderExchange){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
