package com.susanadev.rickymorty.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin

@ExperimentalCoilApi
@Composable
fun DetailView(detail: com.susanadev.domain.model.CharacterInfo) {

    ConstraintLayout() {

        val viewCard = createRef()

        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(viewCard) {
                    top.linkTo(parent.top, margin = 16.dp)
                },
            elevation = 10.dp,
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            ConstraintLayout() {
                val columnConstraint = createRef()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(columnConstraint) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {

                    ConstraintLayout() {
                        val imageConstraint = createRef()
                        CharacterImage(imageUrl = detail.image, modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            )
                            .height(440.dp)
                            .constrainAs(imageConstraint) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })
                    }

                    Text(
                        text = detail.name,
                        Modifier
                            .padding(15.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.h6
                    )
                    Row {
                        Text(
                            text = "Origen ->",
                            Modifier.padding(start = 15.dp, top = 5.dp)
                        )
                        Text(
                            text = detail.origin.name,
                            Modifier.padding(start = 15.dp, top = 5.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "Especie ->",
                            Modifier.padding(start = 15.dp, top = 5.dp)
                        )
                        Text(
                            text = detail.species,
                            Modifier.padding(start = 15.dp, top = 5.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "Estado ->",
                            Modifier.padding(start = 15.dp, top = 5.dp)
                        )
                        Text(
                            text = detail.status,
                            Modifier.padding(start = 15.dp, top = 5.dp)
                        )
                    }
                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailViewPreview() {
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
    DetailView(detail = characterInfo)
}