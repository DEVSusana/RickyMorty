package com.susanadev.rickymorty.presentation.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.presentation.viewModel.ViewModelFactory
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
object FactoryModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(
        application: Application,
        getDetailUseCase: GetDetailUseCase,
        getListOfCharactersUseCase: GetListOfCharactersUseCase,
        getFilteredListOfCharactersUseCase: GetFilteredListOfCharactersUseCase,
        apiService: ApiService

    ): ViewModelProvider.Factory {
        return ViewModelFactory(
            application,
            getDetailUseCase,
            getListOfCharactersUseCase,
            getFilteredListOfCharactersUseCase,
            apiService
        )
    }
}