package com.example.order_service.handler;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super("Order not found with id: " + id);
    }
}
