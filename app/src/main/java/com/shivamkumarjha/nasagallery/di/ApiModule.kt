package com.shivamkumarjha.nasagallery.di

import android.content.Context
import com.google.gson.Gson
import com.shivamkumarjha.nasagallery.config.Constants
import com.shivamkumarjha.nasagallery.network.ApiService
import com.shivamkumarjha.nasagallery.network.HttpInterceptor
import com.shivamkumarjha.nasagallery.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun getApiService(
        @ApplicationContext context: Context,
        gson: Gson,
        httpInterceptor: HttpInterceptor
    ): ApiService {
        val okHttpClient = RetrofitClient.getOkHttpClient(context)
            .addInterceptor(httpInterceptor)
            .build()
        return RetrofitClient.getClient(
            Constants.BASE_URL,
            okHttpClient,
            gson
        ).create(ApiService::class.java)
    }
}