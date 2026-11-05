package com.pedrorodrigo.financehub.budget.dto;

import com.pedrorodrigo.financehub.category.dto.CategoryResponse;

import java.math.BigDecimal;

public record BudgetCategoryResponse(

        Long id,
        BigDecimal limitAmount,
        CategoryResponse category

) {}
