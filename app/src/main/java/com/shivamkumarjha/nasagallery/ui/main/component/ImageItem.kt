package com.shivamkumarjha.nasagallery.ui.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.ui.main.model.MainEvent

@Composable
fun ImageItem(
    nasa: NASA,
    index: Int,
    modifier: Modifier = Modifier,
    event: (MainEvent) -> Unit,
) {
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
                Image(
                    nasa.url,
                    ContentScale.Crop,
                    Modifier
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