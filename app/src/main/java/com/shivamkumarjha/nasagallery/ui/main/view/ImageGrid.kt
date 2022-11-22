package com.shivamkumarjha.nasagallery.ui.main.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.ui.main.component.ImageItem
import com.shivamkumarjha.nasagallery.ui.main.model.MainEvent

@Composable
fun ImageGrid(
    images: List<NASA>,
    modifier: Modifier = Modifier,
    event: (MainEvent) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(images) { nasa ->
            ImageItem(
                nasa,
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                event
            )
        }
    }
}