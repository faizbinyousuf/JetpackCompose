package com.example.learncompose.presentation.products.viewmodel

import com.example.learncompose.domain.model.ProductCategoryResponse
import com.example.learncompose.domain.model.ProductModel

data class ProductState(
    val isLoading :Boolean = false,
    val categoryResponse: ProductCategoryResponse? =null,
    val productModel: ProductModel? =null
)