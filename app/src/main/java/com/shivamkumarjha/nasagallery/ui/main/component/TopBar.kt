package com.shivamkumarjha.nasagallery.ui.main.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(text: MutableState<String>, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = text.value,
                style = MaterialTheme.typography.body1
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = null
                )
            }
        },
        elevation = 0.dp
    )
}