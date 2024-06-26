package com.susanadev.rickymorty.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.usecases.GetDetailUseCase
import com.susanadev.usecases.GetFilteredListOfCharactersUseCase
import com.susanadev.usecases.GetListOfCharactersUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val app: Application,
    private val getDetailUseCase: GetDetailUseCase,
    private val getListOfCharactersUseCase: GetListOfCharactersUseCase,
    private val getFilteredListOfCharactersUseCase: GetFilteredListOfCharactersUseCase,
    private val apiService: ApiService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Application::class.java,
            GetDetailUseCase::class.java,
            GetListOfCharactersUseCase::class.java,
            GetFilteredListOfCharactersUseCase::class.java,
            ApiService::class.java
        ).newInstance(
            app,
            getDetailUseCase,
            getListOfCharactersUseCase,
            getFilteredListOfCharactersUseCase,
            apiService
        )
    }

}