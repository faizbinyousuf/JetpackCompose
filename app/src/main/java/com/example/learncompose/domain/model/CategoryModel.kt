package com.example.learncompose.domain.model



data class ProductCategoryResponse (
    val categories: List<Category>? = null
)

data class Category (
    val idCategory: String? = null,
    val strCategory: String? = null,
    val strCategoryThumb: String? = null,
    val strCategoryDescription: String? = null
)
