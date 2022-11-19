package com.shivamkumarjha.nasagallery.ui.main.component

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.ui.main.model.MainEvent

@Composable
fun ImageItem(
    nasa: NASA,
    index: Int,
    modifier: Modifier = Modifier,
    event: (MainEvent) -> Unit,
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

    Box(modifier = modifier) {
        Card(modifier = Modifier
            .fillMaxSize()
            .clickable {
                event(MainEvent.OpenDetail(index))
            }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = imageRequest,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                Text(
                    text = nasa.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )

                Text(
                    text = nasa.date,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 4.dp),
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 1
                )
            }
        }
    }
}