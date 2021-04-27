package com.shivamkumarjha.nasapictures.repository

import androidx.lifecycle.LiveData
import com.shivamkumarjha.nasapictures.model.NASA

interface DatabaseRepository {
    suspend fun addData(list: List<NASA>)
    suspend fun getData(): List<NASA>
    suspend fun getImages(): LiveData<List<NASA>>
    suspend fun updateBookmark(isBookmarked: Boolean, url: String)
    suspend fun getBookmarkedImages(): LiveData<List<NASA>>
}