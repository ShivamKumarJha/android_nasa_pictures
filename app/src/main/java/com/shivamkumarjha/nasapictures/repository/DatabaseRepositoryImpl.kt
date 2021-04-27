package com.shivamkumarjha.nasapictures.repository

import androidx.lifecycle.LiveData
import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.persistence.NASADao

class DatabaseRepositoryImpl(private val nasaDao: NASADao) : DatabaseRepository {

    override suspend fun addData(list: List<NASA>) {
        for (item in list) {
            nasaDao.addData(item)
        }
    }

    override suspend fun getData(): List<NASA> = nasaDao.getData()

    override suspend fun getImages(): LiveData<List<NASA>> = nasaDao.getImages()

    override suspend fun updateBookmark(isBookmarked: Boolean, url: String) {
        nasaDao.updateBookmark(isBookmarked, url)
    }

    override suspend fun getBookmarkedImages(): LiveData<List<NASA>> = nasaDao.getBookmarkedImages()
}