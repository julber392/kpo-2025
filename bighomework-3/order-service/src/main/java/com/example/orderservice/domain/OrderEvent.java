package com.example.orderservice.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal amount;
    private EventType eventType;
}

