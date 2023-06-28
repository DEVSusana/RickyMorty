package com.susanadev.rickymorty.view.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.utils.Resource
import com.susanadev.rickymorty.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable //TODO REVISAR LO QUE SE VA A MOSTRAR Y COMO
fun DetailView(viewModel: ViewModel, id: Int) {
    LaunchedEffect(id) {
        viewModel.getCharacterDetailResponse(id)
    }
    val detail by viewModel.getCharacterDetail.observeAsState()


    when (detail) {
        is Resource.Success -> {
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
                            (detail as Resource.Success<CharacterInfo>).data
                                ?.let { it1 ->
                                    ConstraintLayout() {
                                        val imageConstraint = createRef()
                                        Image(
                                            painter = rememberAsyncImagePainter(
                                                it1.image
                                            ),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                                                .height(440.dp)
                                                .constrainAs(imageConstraint) {
                                                    top.linkTo(parent.top)
                                                    start.linkTo(parent.start)
                                                    end.linkTo(parent.end)
                                                }
                                        )
                                    }
                                }

                            (detail as Resource.Success<CharacterInfo>).data?.let {
                                Text(
                                    text = it.name,
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
                                        text = it.origin.name,
                                        Modifier.padding(start = 15.dp, top = 5.dp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Especie ->",
                                        Modifier.padding(start = 15.dp, top = 5.dp)
                                    )
                                    Text(
                                        text = it.species,
                                        Modifier.padding(start = 15.dp, top = 5.dp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Estado ->",
                                        Modifier.padding(start = 15.dp, top = 5.dp)
                                    )
                                    Text(
                                        text = it.status,
                                        Modifier.padding(start = 15.dp, top = 5.dp)
                                    )
                                }
                                Spacer(Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }

        is Resource.Loading -> {
            ShowProgressBar()
        }

        is Resource.Error -> {
            (viewModel.getCharacterDetail.value as Resource.Error<*>).message?.let {
                Toast.makeText(LocalContext.current, "An error occurred : $it", Toast.LENGTH_LONG)
                    .show()
                Log.i("ERROR", it)
            }
        }

        else -> {}

    }

}