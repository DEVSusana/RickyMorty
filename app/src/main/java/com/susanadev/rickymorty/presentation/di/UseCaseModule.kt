package com.susanadev.rickymorty.presentation.di

import com.susanadev.domain.repository.Repository
import com.susanadev.usecases.GetDetailUseCase
import com.susanadev.usecases.GetFilteredListOfCharactersUseCase
import com.susanadev.usecases.GetListOfCharactersUseCase
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