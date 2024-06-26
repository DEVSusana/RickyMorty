package com.susanadev.rickymorty.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.presentation.di.ApiUrl
import com.susanadev.rickymorty.presentation.di.NetModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetModule::class]
)
object TestNetModule {
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
    fun provideApiUrl(): String = "https://localhost:8080"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = apiClient

    @Provides
    @Singleton
    fun provideRetrofit(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(apiUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}