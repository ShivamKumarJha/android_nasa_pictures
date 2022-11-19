package com.shivamkumarjha.nasagallery.ui.main.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shivamkumarjha.nasagallery.R
import com.shivamkumarjha.nasagallery.model.Resource
import com.shivamkumarjha.nasagallery.ui.main.model.MainEvent
import com.shivamkumarjha.nasagallery.ui.main.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    event: (MainEvent) -> Unit,
) {
    val images = viewModel.imagesDb.observeAsState()
    val imagesResponse = viewModel.imagesResponse.observeAsState()

    fun callApi() {
        viewModel.callImages()
    }

    LaunchedEffect(Unit) {
        callApi()
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.body1
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = imagesResponse.value is Resource.Error || imagesResponse.value is Resource.Offline
            ) {
                val text = if (imagesResponse.value is Resource.Offline)
                    stringResource(id = R.string.offline)
                else imagesResponse.value?.message ?: stringResource(id = R.string.error_occurred)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.Red.copy(alpha = 0.5f))
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1,
                        color = Color.Companion.White
                    )

                    Button(
                        onClick = { callApi() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(
                            text = stringResource(id = R.string.retry),
                            style = MaterialTheme.typography.button,
                            color = Color.Companion.White
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = imagesResponse.value is Resource.Loading
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .height(2.dp)
                )
            }
        }
    ) {
        if (!images.value.isNullOrEmpty()) {
            ImageGrid(images = images.value!!, Modifier.fillMaxSize()) {
                event(MainEvent.OpenDetail(it))
            }
        }
    }
}