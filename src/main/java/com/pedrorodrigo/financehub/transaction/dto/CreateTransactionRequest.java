package com.pedrorodrigo.financehub.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(

        String description,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotBlank(message = "Transaction type is required")
        String transactionType,

        @NotBlank(message = "Transaction status is required")
        String transactionStatus,

        @NotNull(message = "Transaction date is required")
        LocalDate transactionDate

) {}
