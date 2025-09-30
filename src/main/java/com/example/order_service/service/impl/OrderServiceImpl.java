package com.example.order_service.service.impl;

import com.example.order_service.DTO.*;


import com.example.order_service.handler.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.Status;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderProducer;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;



    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        order.setStatus(Status.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        MessageDTO messageDTO = orderMapper.toMessageDto(savedOrder);
        orderProducer.sendOrder(messageDTO);

        return orderMapper.toDto(savedOrder);
    }
    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(String id, OrderUpdate orderUpdate) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderMapper.partialUpdate(orderUpdate, order);
        order.setUpdatedAt(LocalDateTime.now());

        return orderMapper.toDto(orderRepository.save(order));
    }
    @Override
    @Transactional
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        order.setStatus(Status.DELETED);
        order.setDeletedAt(LocalDateTime.now());
        orderRepository.save(order);
    }
}
