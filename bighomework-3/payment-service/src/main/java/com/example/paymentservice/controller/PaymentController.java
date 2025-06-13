package com.example.paymentservice.controller;

import com.example.paymentservice.dto.*;
import com.example.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/account")
    public ResponseEntity<AccountResponse> createAccount(
            @RequestParam Long userId) {
        AccountResponse response = paymentService.createAccount(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @Valid @RequestBody DepositRequest request) {
        AccountResponse response = paymentService.deposit(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BalanceResponse> getBalance(
            @PathVariable Long userId) {
        BalanceResponse response = paymentService.getBalance(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(
            @Valid @RequestBody PaymentRequest request) {
        paymentService.processPayment(request);
        return ResponseEntity.ok("Payment processed successfully");
    }
}