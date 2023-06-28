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
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.susanadev.rickymorty.data.model.CharacterInfo

@ExperimentalCoilApi
@Composable
fun CharacterImage(detail: CharacterInfo) {
    Image(
        painter = rememberAsyncImagePainter(
            detail.image
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .height(140.dp)
            .width(140.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )

}