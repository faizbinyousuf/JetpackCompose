package com.example.learncompose.presentation.products.viewmodel

sealed class ProductEvent {
    data object  LoadCategories: ProductEvent()
    data class LoadProductsByCategory(val category: String) : ProductEvent()
}