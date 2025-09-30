package com.example.order_service.service.impl;

import com.example.order_service.DTO.MessageDTO;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.Status;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumerImpl implements OrderConsumer {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @RabbitListener(queues = "order.queue",concurrency = "3-10")
    @Override
    public void consumeMessage(MessageDTO messageDTO) {
        log.info("Received message: {}", messageDTO);

        try {
            processOrder(messageDTO);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private void processOrder(MessageDTO messageDTO) {
        Order order = orderMapper.toEntity(messageDTO);

        if (order.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            order.setStatus(Status.APPROVED);
        } else {
            order.setStatus(Status.REJECTED);
        }

        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);

        log.info("Order [{}] final status: {}", order.getId(), order.getStatus());
    }

}
