package com.example.order_service.DTO;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        Integer quantity,
        BigDecimal unitPrice
) {}