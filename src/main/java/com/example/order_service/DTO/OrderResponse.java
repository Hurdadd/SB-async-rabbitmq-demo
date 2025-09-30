package com.example.order_service.DTO;

import com.example.order_service.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        Status status,
        BigDecimal amount,
        String product,
        LocalDateTime createdAt
) {
}
