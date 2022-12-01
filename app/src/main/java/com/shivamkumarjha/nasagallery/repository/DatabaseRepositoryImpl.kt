package com.shivamkumarjha.nasagallery.repository

import androidx.lifecycle.LiveData
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.persistence.AppDatabase

class DatabaseRepositoryImpl(
    private val database: AppDatabase
) : DatabaseRepository {

    override suspend fun addImage(nasa: NASA) {
        database.nasaDao().addImage(nasa)
    }

    override fun getImages(): LiveData<List<NASA>?> {
        return database.nasaDao().getImages()
    }

    override suspend fun updateBookmark(bookmark: Boolean, url: String) {
        database.nasaDao().updateBookmark(bookmark, url)
    }

    override fun getBookmarks(): LiveData<List<NASA>?> {
        return database.nasaDao().getBookmarks()
    }
}