package com.pedrorodrigo.financehub.transaction.dto;

import com.pedrorodrigo.financehub.transaction.TransactionStatus;
import com.pedrorodrigo.financehub.transaction.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(

        @NotNull(message = "Category is required")
        Long categoryId,

        String description,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotNull(message = "Transaction type is required")
        TransactionType transactionType,

        @NotNull(message = "Transaction status is required")
        TransactionStatus transactionStatus,

        @NotNull(message = "Transaction date is required")
        LocalDate transactionDate

) {}
