package com.shivamkumarjha.nasapictures.repository

import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.persistence.NASADao

class DatabaseRepositoryImpl(private val nasaDao: NASADao) : DatabaseRepository {

    override suspend fun addData(list: ArrayList<NASA>) {
        for (item in list) {
            nasaDao.addData(item)
        }
    }

    override suspend fun getData(): ArrayList<NASA> {
        return nasaDao.getData() as ArrayList<NASA>
    }
}