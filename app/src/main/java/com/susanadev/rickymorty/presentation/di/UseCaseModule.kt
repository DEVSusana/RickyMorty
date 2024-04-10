package com.susanadev.rickymorty.presentation.di

import com.susanadev.rickymorty.domain.repository.Repository
import com.susanadev.rickymorty.domain.usecase.GetDetailUseCase
import com.susanadev.rickymorty.domain.usecase.GetFilteredListOfCharactersUseCase
import com.susanadev.rickymorty.domain.usecase.GetListOfCharactersUseCase
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

    @Provides
    @Singleton
    fun provideGetListOfCharactersUseCase(
        repository: Repository
    ): GetListOfCharactersUseCase {
        return GetListOfCharactersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFilteredListOfCharactersUseCase(
        repository: Repository
    ): GetFilteredListOfCharactersUseCase {
        return GetFilteredListOfCharactersUseCase(repository)
    }

}