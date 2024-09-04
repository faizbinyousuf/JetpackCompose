package com.example.learncompose.core.di

import com.example.learncompose.data.remote.ProductApi
import com.example.learncompose.data.repository.ProductRepositoryImpl
import com.example.learncompose.domain.repository.ProductRepository
import com.example.learncompose.domain.usecase.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule{
    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApi:ProductApi):ProductRepository{
        return ProductRepositoryImpl(productApi)
    }
    @Provides
    @Singleton
    fun provideProductUseCase(repository: ProductRepository): ProductUseCase {
        return ProductUseCase(repository)
    }

}