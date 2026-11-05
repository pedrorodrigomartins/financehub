package com.pedrorodrigo.financehub.category.dto;

import com.pedrorodrigo.financehub.category.CategoryType;

public record CategoryResponse(

        Long id,
        String name,
        CategoryType categoryType

) {}
