package com.susanadev.rickymorty.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable
fun DisplayList(
    navController: NavController, viewModel: ViewModel,
    state: MutableState<TextFieldValue>, modifier: Modifier = Modifier
) {
    val selectedIndex by remember { mutableIntStateOf(-1) }
    val resultItems: LazyPagingItems<CharacterInfo> = if (state.value.text.isNotEmpty()) {
        viewModel.resultSearchList.collectAsLazyPagingItems()
    } else {
        viewModel.resultCharacterList.collectAsLazyPagingItems()
    }

    Surface(color = Color.LightGray) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                count = resultItems.itemCount
            ) { index ->
                val item = resultItems[index]
                if (item != null) {
                    ListItem(navController = navController, detail = item, index, selectedIndex)
                }
            }
        }
    }
    resultItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                //You can add modifier to manage load state when first time response page is loading
                ShowProgressBar()
            }

            loadState.append is LoadState.Loading -> {
                //You can add modifier to manage load state when next response page is loading
                ShowProgressBar()
            }

            loadState.append is LoadState.Error -> {
                //You can use modifier to show error message
            }
        }
    }


}