package com.susanadev.rickymorty.presentation.di

import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.data.repository.dataSource.RemoteDataSource
import com.susanadev.rickymorty.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }
}