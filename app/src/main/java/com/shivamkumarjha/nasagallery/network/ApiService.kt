package com.shivamkumarjha.nasagallery.network

import com.shivamkumarjha.nasagallery.model.NASA
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("nasa-pictures.json")
    suspend fun getNASAData(): Response<List<NASA>>
}