package com.example.order_service.service;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.DTO.OrderUpdate;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrder(String id);
    OrderResponse updateOrder(String id, OrderUpdate orderUpdate);
    void deleteOrder(String id);
    List<OrderResponse> getAllOrders();

}
