package com.example.learncompose.domain.model



data class ProductModel (
    val meals: List<Meal>? = null
)

data class Meal (
    val strMeal: String? = null,
    val strMealThumb: String? = null,
    val idMeal: String? = null
)
