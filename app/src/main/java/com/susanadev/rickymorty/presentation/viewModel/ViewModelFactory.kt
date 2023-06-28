package com.susanadev.rickymorty.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.susanadev.rickymorty.domain.usecase.GetDetailUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val app: Application,
    private val getDetailUseCase: GetDetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Application::class.java,
            GetDetailUseCase::class.java
        ).newInstance(
            app,
            getDetailUseCase
        )
    }

}