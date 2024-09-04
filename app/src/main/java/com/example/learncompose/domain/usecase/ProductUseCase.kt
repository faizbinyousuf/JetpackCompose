package com.example.learncompose.domain.usecase

import com.example.learncompose.domain.model.ProductCategoryResponse
import com.example.learncompose.domain.model.ProductModel
import com.example.learncompose.domain.repository.ProductRepository
import javax.inject.Inject

data class ProductUseCase @Inject constructor( private val productRepository: ProductRepository) {
    suspend operator fun invoke(): ProductCategoryResponse {
        return productRepository.getProductCategory()
    }

    suspend  fun getProductsInCategory(category:String): ProductModel {
        return productRepository.getProducts(category)
    }


}