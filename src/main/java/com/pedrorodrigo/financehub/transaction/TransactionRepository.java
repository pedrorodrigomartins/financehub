package com.pedrorodrigo.financehub.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdAndUser_Id(
            Long transactionId,
            Long userId
    );

    Page<Transaction> findByUser_IdAndTransactionDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    Page<Transaction> findByUser_IdAndCategory_IdAndTransactionDateBetween(
            Long userId,
            Long categoryId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    Page<Transaction> findByUser_IdAndTransactionStatus(
            Long userId,
            TransactionStatus status,
            Pageable pageable
    );

    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Transaction t
    WHERE t.user.id = :userId
      AND t.transactionType = :type
      AND t.transactionStatus = :status
      AND t.transactionDate BETWEEN :startDate AND :endDate
    """)
    BigDecimal sumAmountByFilters(
            @Param("userId") Long userId,
            @Param("type") TransactionType type,
            @Param("status") TransactionStatus status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
    SELECT AVG(t.amount)
    FROM Transaction t
    WHERE t.user.id = :userId
      AND t.transactionType = :type
      AND t.transactionStatus = :status
      AND t.transactionDate BETWEEN :startDate AND :endDate
    """)
    Double averageAmountByFilters(
            @Param("userId") Long userId,
            @Param("type") TransactionType type,
            @Param("status") TransactionStatus status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
