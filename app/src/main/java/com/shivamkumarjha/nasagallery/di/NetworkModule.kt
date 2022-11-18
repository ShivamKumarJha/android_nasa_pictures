package com.shivamkumarjha.nasagallery.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shivamkumarjha.nasagallery.network.HttpInterceptor
import com.shivamkumarjha.nasagallery.network.NetworkHelper
import com.shivamkumarjha.nasagallery.network.NoConnectivityException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(connectivityManager: ConnectivityManager): NetworkHelper {
        return NetworkHelper(connectivityManager)
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(networkHelper: NetworkHelper): HttpInterceptor {
        return HttpInterceptor(networkHelper, NoConnectivityException())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .serializeSpecialFloatingPointValues()
        .create()
}