package com.pedrorodrigo.financehub.budget.dto;

import com.pedrorodrigo.financehub.user.dto.UserResponse;

import java.time.LocalDate;

public record BudgetResponse(

        Long id,
        LocalDate budgetDate,
        UserResponse user

) {}
