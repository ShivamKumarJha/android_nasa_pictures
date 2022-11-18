package com.shivamkumarjha.nasagallery.ui.main.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.ui.main.component.ImageItem

@Composable
fun ImageGrid(
    images: List<NASA>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(images) { index, nasa ->
            ImageItem(
                nasa,
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                onClick(index)
            }
        }
    }
}