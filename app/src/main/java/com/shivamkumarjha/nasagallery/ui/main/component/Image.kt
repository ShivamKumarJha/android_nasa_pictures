package com.shivamkumarjha.nasagallery.ui.main.component

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun Image(
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .decoderFactory(
            if (Build.VERSION.SDK_INT >= 28)
                ImageDecoderDecoder.Factory()
            else
                GifDecoder.Factory()
        )
        .crossfade(true)
        .build()

    SubcomposeAsyncImage(
        model = imageRequest,
        loading = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        },
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
    )
}