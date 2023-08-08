package com.susanadev.rickymorty.view.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.utils.Resource
import com.susanadev.rickymorty.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    viewModel: ViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "list",
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
                val resultItems: LazyPagingItems<CharacterInfo> =
                    if (textState.value.text.isNotEmpty()) {
                        viewModel.resultSearchList.collectAsLazyPagingItems()
                    } else {
                        viewModel.resultCharacterList.collectAsLazyPagingItems()
                    }
                DisplayList(
                    navController = navController,
                    resultItems,
                    modifier = Modifier.padding(padding)
                )
            }
        }
        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let {
                LaunchedEffect(it) {
                    viewModel.getCharacterDetailResponse(it)
                }
            }
            val detail by viewModel.getCharacterDetail.observeAsState()
            when (detail) {
                is Resource.Success -> {
                    (detail as Resource.Success<CharacterInfo>).data?.let { it1 ->
                        DetailView(
                            it1
                        )
                        viewModel.invalidateResultDataSource()
                    }
                }

                is Resource.Loading -> {
                    ShowProgressBar()
                }

                is Resource.Error -> {
                    (detail as Resource.Error<*>).message?.let {
                        Toast.makeText(
                            LocalContext.current,
                            "An error occurred : $it",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Log.i("ERROR", it)
                    }
                    viewModel.invalidateResultDataSource()
                }

                else -> {
                }
            }
            DisposableEffect(Unit) {
                onDispose {
                    viewModel.invalidateResultDataSource()
                }
            }
        }
    }

}