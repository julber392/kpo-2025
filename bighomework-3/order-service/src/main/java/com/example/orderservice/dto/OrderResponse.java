package com.example.orderservice.dto;

import com.example.orderservice.domain.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String description;
    private OrderStatus status;
    private LocalDateTime createdAt;
}