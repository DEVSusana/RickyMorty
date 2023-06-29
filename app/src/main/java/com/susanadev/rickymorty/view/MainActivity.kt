package com.susanadev.rickymorty.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.susanadev.rickymorty.presentation.viewModel.ViewModel
import com.susanadev.rickymorty.presentation.viewModel.ViewModelFactory
import com.susanadev.rickymorty.view.ui.theme.RickyMortyTheme
import com.susanadev.rickymorty.view.compose.NavigationComponent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var viewModel: ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("CheckResult")
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
        setContent {
            RickyMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavigationComponent(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
