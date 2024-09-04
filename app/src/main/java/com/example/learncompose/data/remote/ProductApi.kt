package com.example.learncompose.data.remote

import com.example.learncompose.domain.model.ProductCategoryResponse
import com.example.learncompose.domain.model.ProductModel
import  retrofit2.http.GET
import retrofit2.http.Query

interface  ProductApi{

    @GET("categories.php")
    suspend fun getProductCategory():ProductCategoryResponse

    @GET("filter.php")
    suspend fun getProductsByCategory(
        @Query("c") category: String
    ): ProductModel
}