package com.shivamkumarjha.nasagallery.ui.main.model

sealed class MainEvent {
    data class OpenDetail(val url: String) : MainEvent()
}