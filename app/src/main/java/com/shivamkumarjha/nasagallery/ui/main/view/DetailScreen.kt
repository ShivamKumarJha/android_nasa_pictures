package com.shivamkumarjha.nasagallery.ui.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.shivamkumarjha.nasagallery.ui.main.component.DetailItem
import com.shivamkumarjha.nasagallery.ui.main.component.DetailTopBar
import com.shivamkumarjha.nasagallery.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(viewModel: MainViewModel, index: Int, onBack: () -> Unit) {
    val images = viewModel.imagesDb.observeAsState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    if (!images.value.isNullOrEmpty()) {
        Scaffold(
            topBar = {
                DetailTopBar {
                    onBack()
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                HorizontalPager(
                    count = images.value!!.size,
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                ) { page ->
                    val nasa = images.value!![page]
                    DetailItem(nasa = nasa,
                        Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                // Calculate the absolute offset for the current page from the
                                // scroll position. We use the absolute value which allows us to mirror
                                // any effects for both directions
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                                // We animate the scaleX + scaleY, between 85% and 100%
                                lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }

                                // We animate the alpha, between 50% and 100%
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            })
                }

                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 12.dp)
                ) {
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        indicatorWidth = 8.dp,
                        spacing = 4.dp
                    )
                }
            }
        }

        //Scroll to index
        LaunchedEffect(Unit) {
            scope.launch {
                pagerState.scrollToPage(index)
            }
        }
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}