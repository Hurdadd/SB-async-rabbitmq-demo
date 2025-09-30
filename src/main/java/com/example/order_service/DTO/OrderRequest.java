package com.example.order_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequest(
        @NotNull
        BigDecimal amount,
        @NotBlank
        String product
) {}
