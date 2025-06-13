package com.example.paymentservice.repository;

import com.example.paymentservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Находит аккаунт по user_id с пессимистичной блокировкой
     * для обеспечения атомарности операций с балансом
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.userId = :userId")
    Optional<Account> findByUserIdWithLock(@Param("userId") Long userId);

    /**
     * Находит аккаунт по user_id (без блокировки)
     */
    Optional<Account> findByUserId(Long userId);

    /**
     * Проверяет существование аккаунта по user_id
     */
    boolean existsByUserId(Long userId);
}
