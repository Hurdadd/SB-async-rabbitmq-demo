package com.example.order_service.service.impl;


import com.example.order_service.DTO.MessageDTO;
import com.example.order_service.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducerImpl implements OrderProducer {
    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "order.exchange";
    private static final String ROUTING_KEY = "order.created";

    @Async("producerExecutor")
    public void sendOrder(MessageDTO orderDto) {
        System.out.println("jjjjj");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, orderDto);
    }
}
