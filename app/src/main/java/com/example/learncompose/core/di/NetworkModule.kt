package com.example.learncompose.core.di

import com.example.learncompose.data.remote.ProductApi
import dagger.Module
import  dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    private const val BASE_URL ="https://www.themealdb.com/api/json/v1/1/"


    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
}