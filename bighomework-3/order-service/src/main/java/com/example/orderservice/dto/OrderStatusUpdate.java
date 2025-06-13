package com.example.orderservice.dto;

import com.example.orderservice.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusUpdate {
    private Long orderId;
    private OrderStatus status;
}