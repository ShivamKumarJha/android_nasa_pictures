package com.shivamkumarjha.nasagallery.repository

import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.model.Resource
import kotlinx.coroutines.flow.Flow

interface NASARepository {
    suspend fun getImages(): Flow<Resource<List<NASA>?>>
}