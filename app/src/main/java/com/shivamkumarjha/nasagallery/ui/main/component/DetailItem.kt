package com.shivamkumarjha.nasagallery.ui.main.component

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.shivamkumarjha.nasagallery.model.NASA

@Composable
fun DetailItem(
    nasa: NASA,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(nasa.url)
        .decoderFactory(
            if (Build.VERSION.SDK_INT >= 28)
                ImageDecoderDecoder.Factory()
            else
                GifDecoder.Factory()
        )
        .crossfade(true)
        .build()

    Card(modifier = modifier) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    SubcomposeAsyncImage(
                        model = imageRequest,
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                    )

                    if (!nasa.copyright.isNullOrEmpty()) {
                        Box(
                            Modifier
                                .background(Color.Black)
                                .align(Alignment.BottomEnd)
                        ) {
                            Text(
                                text = nasa.copyright,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(horizontal = 4.dp, vertical = 2.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }

                }
            }

            item {
                Text(
                    text = nasa.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.subtitle1,
                )
            }

            item {
                Text(
                    text = nasa.date,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.subtitle2,
                )
            }

            item {
                Text(
                    text = nasa.explanation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.body2,
                )
            }

        }
    }
}