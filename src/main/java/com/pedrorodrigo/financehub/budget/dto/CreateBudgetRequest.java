package com.pedrorodrigo.financehub.budget.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateBudgetRequest(

        @NotNull(message = "Budget date is required")
        LocalDate budgetDate

) {}
