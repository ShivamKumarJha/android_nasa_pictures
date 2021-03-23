package com.shivamkumarjha.nasapictures.network

import com.shivamkumarjha.nasapictures.model.NASA
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("NASA.json")
    suspend fun getNASAData(): Response<List<NASA>>
}