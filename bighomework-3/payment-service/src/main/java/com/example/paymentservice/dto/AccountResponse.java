package com.example.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {
    private Long id;
    private Long userId;
    private BigDecimal balance;
}