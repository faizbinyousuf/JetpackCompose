package com.example.learncompose.domain.repository


import com.example.learncompose.domain.model.ProductCategoryResponse
import com.example.learncompose.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProductCategory(): ProductCategoryResponse
    suspend fun getProducts(category:String): ProductModel

}
