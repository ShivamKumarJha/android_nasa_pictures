package com.shivamkumarjha.nasapictures.repository

import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.network.Resource
import kotlinx.coroutines.flow.Flow

interface NASARepository {
    suspend fun getNASAData(): Flow<Resource<ArrayList<NASA>?>>
}