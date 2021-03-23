package com.shivamkumarjha.nasapictures.repository

import com.shivamkumarjha.nasapictures.model.NASA

interface DatabaseRepository {
    suspend fun addData(list: List<NASA>)
    suspend fun getData(): List<NASA>
}