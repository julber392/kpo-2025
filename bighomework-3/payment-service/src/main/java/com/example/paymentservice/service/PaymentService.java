package com.example.paymentservice.service;

import com.example.paymentservice.domain.Account;
import com.example.paymentservice.dto.*;
import com.example.paymentservice.exception.PaymentProcessingException;
import com.example.paymentservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;
    private final PaymentEventPublisher eventPublisher;

    @Transactional
    public AccountResponse createAccount(Long userId) {
        if (accountRepository.existsByUserId(userId)) {
            throw new PaymentProcessingException("Account already exists");
        }

        Account account = new Account();
        account.setUserId(userId);
        account.setBalance(BigDecimal.ZERO);

        Account savedAccount = accountRepository.save(account);
        return toAccountResponse(savedAccount);
    }

    @Transactional
    public AccountResponse deposit(DepositRequest request) {
        Account account = accountRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new PaymentProcessingException("Account not found"));

        account.setBalance(account.getBalance().add(request.getAmount()));
        Account updatedAccount = accountRepository.save(account);

        return toAccountResponse(updatedAccount);
    }

    public BalanceResponse getBalance(Long userId) {
        BigDecimal balance = accountRepository.findByUserId(userId)
                .map(Account::getBalance)
                .orElseThrow(() -> new PaymentProcessingException("Account not found"));

        BalanceResponse response = new BalanceResponse();
        response.setUserId(userId);
        response.setBalance(balance);
        return response;
    }

    @Transactional
    public void processPayment(PaymentRequest request) {
        Account account = accountRepository.findByUserIdWithLock(request.getUserId())
                .orElseThrow(() -> new PaymentProcessingException("Account not found"));

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            eventPublisher.publishPaymentFailedEvent(request.getOrderId(), request.getUserId());
            throw new PaymentProcessingException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);
        eventPublisher.publishPaymentSuccessEvent(request.getOrderId(), request.getUserId());
    }
    @Transactional
    public void processPayment(Long orderId, Long userId, BigDecimal amount) {
        PaymentRequest request = new PaymentRequest();
        request.setOrderId(orderId);
        request.setUserId(userId);
        request.setAmount(amount);
        processPayment(request);
    }

    private AccountResponse toAccountResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setUserId(account.getUserId());
        response.setBalance(account.getBalance());
        return response;
    }
}