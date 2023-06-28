package com.susanadev.rickymorty.presentation.di

import com.susanadev.rickymorty.domain.repository.Repository
import com.susanadev.rickymorty.domain.usecase.GetDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetDetailUseCase(
        repository: Repository
    ): GetDetailUseCase {
        return GetDetailUseCase(repository)
    }

}