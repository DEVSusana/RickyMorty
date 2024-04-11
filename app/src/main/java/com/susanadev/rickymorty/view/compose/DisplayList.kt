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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin
import com.susanadev.rickymorty.presentation.viewModel.ViewModel
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoilApi
@Composable
fun DisplayList(
    navController: NavController,
    resultItems: LazyPagingItems<com.susanadev.domain.model.CharacterInfo>,
    modifier: Modifier = Modifier
) {
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
                    ListItem(navController = navController, detail = item)
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

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DisplayListPreview() {
    val characterInfo = com.susanadev.domain.model.CharacterInfo(
        created = "2023-07-03",
        episode = listOf("S01E01", "S01E02"),
        gender = "Male",
        id = 123,
        image = "https://rickandmortyapi.com/api/character/avatar/79.jpeg",
        location = com.susanadev.domain.model.Location(
            "tierra",
            "https://example.com/character/123"
        ),
        name = "Rick Sanchez",
        origin = com.susanadev.domain.model.Origin("tierra", "https://example.com/character/123"),
        species = "Human",
        status = "Alive",
        type = "Main Character",
        url = "https://example.com/character/123"
    )
    DisplayList(
        navController = rememberNavController(),
        resultItems = flowOf(PagingData.from(listOf(characterInfo))).collectAsLazyPagingItems()
    )
}