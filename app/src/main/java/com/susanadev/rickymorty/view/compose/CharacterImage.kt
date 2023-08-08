package com.susanadev.rickymorty.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.model.Location
import com.susanadev.rickymorty.data.model.Origin

@ExperimentalCoilApi
@Composable
fun CharacterImage(detail: CharacterInfo, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(
            detail.image
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )

}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterImagePreview(){
    val characterInfo = CharacterInfo(
        created = "2023-07-03",
        episode = listOf("S01E01", "S01E02"),
        gender = "Male",
        id = 123,
        image = "https://rickandmortyapi.com/api/character/avatar/79.jpeg",
        location = Location("tierra", "https://example.com/character/123"),
        name = "Rick Sanchez",
        origin = Origin("tierra", "https://example.com/character/123"),
        species = "Human",
        status = "Alive",
        type = "Main Character",
        url = "https://example.com/character/123"
    )

    CharacterImage(
        detail = characterInfo, modifier = Modifier
        .padding(4.dp)
        .height(140.dp)
        .width(140.dp)
        .clip(RoundedCornerShape(corner = CornerSize(10.dp))))
}