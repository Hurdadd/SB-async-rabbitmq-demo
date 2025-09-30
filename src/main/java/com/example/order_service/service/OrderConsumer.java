package com.example.order_service.service;

import com.example.order_service.DTO.MessageDTO;

public interface OrderConsumer {
    void consumeMessage(MessageDTO orderDto);
}
