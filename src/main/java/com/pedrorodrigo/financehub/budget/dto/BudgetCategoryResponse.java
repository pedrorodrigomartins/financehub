package com.pedrorodrigo.financehub.budget.dto;

import com.pedrorodrigo.financehub.category.dto.CategoryResponse;
import com.pedrorodrigo.financehub.user.dto.UserResponse;

import java.math.BigDecimal;

public record BudgetCategoryResponse(

        Long id,
        BigDecimal limitAmount,
        CategoryResponse category,
        BudgetResponse budget,
        UserResponse user

) {}
