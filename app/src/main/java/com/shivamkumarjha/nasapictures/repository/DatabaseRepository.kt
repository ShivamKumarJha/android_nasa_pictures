package com.shivamkumarjha.nasapictures.repository

import com.shivamkumarjha.nasapictures.model.NASA

interface DatabaseRepository {
    suspend fun addData(list: ArrayList<NASA>)
    suspend fun getData(): ArrayList<NASA>
}