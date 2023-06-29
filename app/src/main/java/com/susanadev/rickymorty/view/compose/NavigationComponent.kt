package com.susanadev.rickymorty.view.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.susanadev.rickymorty.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    viewModel: ViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            val textState = remember { mutableStateOf(TextFieldValue("")) }
            Scaffold(topBar = {
                TopAppBar(
                    backgroundColor = Color.DarkGray
                ) {
                    SearchView(textState, viewModel)
                }
            }) { padding ->
                DisplayList(
                    navController = navController,
                    viewModel,
                    state = textState,
                    modifier = Modifier.padding(padding)
                )
            }
        }
        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let {
                DetailView(viewModel, it)
            }
        }
    }

}