package com.example.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceResponse {
    private Long userId;
    private BigDecimal balance;
}