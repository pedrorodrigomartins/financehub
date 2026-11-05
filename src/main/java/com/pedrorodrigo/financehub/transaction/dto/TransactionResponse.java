package com.pedrorodrigo.financehub.transaction.dto;

import com.pedrorodrigo.financehub.transaction.TransactionStatus;
import com.pedrorodrigo.financehub.transaction.TransactionType;
import com.pedrorodrigo.financehub.user.dto.UserResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(

        Long id,
        String description,
        BigDecimal amount,
        TransactionType transactionType,
        TransactionStatus transactionStatus,
        LocalDate transactionDate,
        UserResponse user

) {}
