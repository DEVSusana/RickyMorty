package com.susanadev.rickymorty.view.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.susanadev.rickymorty.R
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListItem(
    navController: NavController,
    detail: com.susanadev.domain.model.CharacterInfo
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Surface(color = colorResource(R.color.lightBlueGray)) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate("details/${detail.id}") },
                verticalAlignment = Alignment.CenterVertically
            ) {
                CharacterImage(imageUrl = detail.image, modifier = Modifier
                    .padding(4.dp)
                    .height(140.dp)
                    .width(140.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(10.dp))))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = detail.name, style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
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
    ListItem(
        navController = rememberNavController(),
        detail = characterInfo
    )
}