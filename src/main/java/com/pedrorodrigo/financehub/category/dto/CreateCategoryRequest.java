package com.pedrorodrigo.financehub.category.dto;

import com.pedrorodrigo.financehub.category.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryRequest(

        @NotBlank(message = "Category name cannot be blank")
        String name,

        @NotNull(message = "Category type is required")
        CategoryType categoryType

) {}
