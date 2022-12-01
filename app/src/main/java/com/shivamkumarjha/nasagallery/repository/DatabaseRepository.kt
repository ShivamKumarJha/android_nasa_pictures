package com.shivamkumarjha.nasagallery.repository

import androidx.lifecycle.LiveData
import com.shivamkumarjha.nasagallery.model.NASA

interface DatabaseRepository {
    suspend fun addImage(nasa: NASA)
    fun getImages(): LiveData<List<NASA>?>
    suspend fun updateBookmark(bookmark: Boolean, url: String)
    fun getBookmarks(): LiveData<List<NASA>?>
}