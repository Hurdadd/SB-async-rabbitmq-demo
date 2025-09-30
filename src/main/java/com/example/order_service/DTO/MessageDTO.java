package com.example.order_service.DTO;

import com.example.order_service.model.Status;

import java.math.BigDecimal;

public record MessageDTO(
        String orderId,
        BigDecimal amount,
        String product,
        Status status
) {}

