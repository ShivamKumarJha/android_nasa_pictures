package com.shivamkumarjha.nasagallery.ui.main.model

import com.shivamkumarjha.nasagallery.model.NASA

sealed class MainEvent {
    data class OpenDetail(val url: String) : MainEvent()
    object OpenBookmarks : MainEvent()
    object NavigateUp : MainEvent()
    data class UpdateBookmark(val nasa: NASA) : MainEvent()
}