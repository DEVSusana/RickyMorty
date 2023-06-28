package com.susanadev.rickymorty.presentation.di

import com.susanadev.rickymorty.data.repository.RepositoryImpl
import com.susanadev.rickymorty.data.repository.dataSource.RemoteDataSource
import com.susanadev.rickymorty.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(remoteDataSource)
    }
}