package com.susanadev.rickymorty.presentation.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.susanadev.rickymorty.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val apiClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        connectTimeout(44, TimeUnit.SECONDS)
        readTimeout(44, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideApiService(@ApiUrl apiUrl: String): ApiService {
        return Retrofit.Builder()
            .client(apiClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(apiUrl)
            .build()
            .create()
    }

}