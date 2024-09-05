package com.example.learncompose.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel (
    val meals: List<Meal>? = null
) : Parcelable

@Parcelize
data class Meal (
    val strMeal: String? = null,
    val strMealThumb: String? = null,
    val idMeal: String? = null
) : Parcelable
