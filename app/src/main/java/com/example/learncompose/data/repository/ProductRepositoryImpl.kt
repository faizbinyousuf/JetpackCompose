package com.example.learncompose.data.repository

import com.example.learncompose.data.remote.ProductApi
import com.example.learncompose.domain.model.ProductCategoryResponse
import com.example.learncompose.domain.model.ProductModel
import com.example.learncompose.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl   @Inject constructor(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getProductCategory(): ProductCategoryResponse {
        return productApi.getProductCategory()
    }

    override suspend fun getProducts(category:String): ProductModel {
        return productApi.getProductsByCategory(category)
    }

}