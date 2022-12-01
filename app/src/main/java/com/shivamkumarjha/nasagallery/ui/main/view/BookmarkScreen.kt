package com.shivamkumarjha.nasagallery.ui.main.view


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.shivamkumarjha.nasagallery.R
import com.shivamkumarjha.nasagallery.ui.main.component.TopBar
import com.shivamkumarjha.nasagallery.ui.main.model.MainEvent
import com.shivamkumarjha.nasagallery.ui.main.viewmodel.MainViewModel

@Composable
fun BookmarkScreen(
    viewModel: MainViewModel,
    event: (MainEvent) -> Unit,
) {
    val context = LocalContext.current
    val title = remember { mutableStateOf(context.getString(R.string.bookmarks)) }
    val bookmarks = viewModel.bookmarks.observeAsState()

    Scaffold(
        topBar = {
            TopBar(title) {
                event(MainEvent.NavigateUp)
            }
        }
    ) {
        if (!bookmarks.value.isNullOrEmpty()) {
            ImageGrid(images = bookmarks.value!!, Modifier.fillMaxSize(), event)
        }
    }
}