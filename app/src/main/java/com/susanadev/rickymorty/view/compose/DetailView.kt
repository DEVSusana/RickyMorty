package com.susanadev.rickymorty.view.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
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
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = 10.dp,
                shape = RoundedCornerShape(corner = CornerSize(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    (detail as Resource.Success<CharacterInfo>).data
                        ?.let { it1 -> CharacterImage(detail = it1) }
                    Column {
                        (detail as Resource.Success<CharacterInfo>).data?.let {
                            Text(text = it.name, style = MaterialTheme.typography.h6)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = it.gender, Modifier.padding(15.dp))
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