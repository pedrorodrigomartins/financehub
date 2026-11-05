package com.pedrorodrigo.financehub.budget.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateBudgetCategoryRequest(

        @NotNull(message = "Limit amount is required")
        @DecimalMin(value = "0.00", inclusive = false, message = "Limit amount must be greater than zero")
        BigDecimal limitAmount,

        @NotNull(message = "Category is required")
        Long categoryId

) {}
