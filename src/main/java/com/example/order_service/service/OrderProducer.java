package com.example.order_service.service;

import com.example.order_service.DTO.MessageDTO;



public interface OrderProducer {
     void sendOrder(MessageDTO orderDto);
}





