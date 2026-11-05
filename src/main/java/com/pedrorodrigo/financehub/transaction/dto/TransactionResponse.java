package com.pedrorodrigo.financehub.transaction.dto;

import com.pedrorodrigo.financehub.user.dto.UserResponse;

public record TransactionResponse(

        Long id,
        String description,
        String amount,
        String transactionType,
        String transactionStatus,
        String transactionDate,
        UserResponse user

) {}
