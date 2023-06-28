package com.susanadev.rickymorty.presentation.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.susanadev.rickymorty.domain.usecase.GetDetailUseCase
import com.susanadev.rickymorty.presentation.viewModel.ViewModelFactory
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
        getDetailUseCase: GetDetailUseCase

    ): ViewModelProvider.Factory {
        return ViewModelFactory(
            application,
            getDetailUseCase
        )
    }
}