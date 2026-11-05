package com.pedrorodrigo.financehub.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(

        @NotBlank(message = "Category name cannot be blank")
        String name,

        @NotBlank(message = "Category type cannot be blank")
        String categoryName

) {}
