package com.shivamkumarjha.nasapictures.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shivamkumarjha.nasapictures.BuildConfig
import com.shivamkumarjha.nasapictures.config.Constants
import com.shivamkumarjha.nasapictures.network.*
import com.shivamkumarjha.nasapictures.persistence.NASADatabase
import com.shivamkumarjha.nasapictures.repository.NASARepository
import com.shivamkumarjha.nasapictures.repository.NASARepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun getConnectivityManager(@ApplicationContext context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun getNetworkHelper(connectivityManager: ConnectivityManager) =
        NetworkHelper(connectivityManager)

    @Provides
    @Singleton
    fun getConnectionLiveData(
        connectivityManager: ConnectivityManager,
        networkHelper: NetworkHelper
    ) = ConnectionLiveData(connectivityManager, networkHelper)

    @Provides
    @Singleton
    fun getNoConnectivityException(@ApplicationContext context: Context) =
        NoConnectivityException(context)

    @Provides
    @Singleton
    fun getHttpInterceptor(
        networkHelper: NetworkHelper,
        noConnectivityException: NoConnectivityException
    ) = HttpInterceptor(networkHelper, noConnectivityException)

    @Provides
    @Singleton
    fun getGson(): Gson = GsonBuilder().setLenient()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @Singleton
    fun getCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun getOkHTTPClient(cache: Cache, httpInterceptor: HttpInterceptor): OkHttpClient {
        //Logging
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        //OkHttpClient
        val client = OkHttpClient.Builder()
        client.connectTimeout(5, TimeUnit.MINUTES)
        client.readTimeout(5, TimeUnit.MINUTES)
        client.addInterceptor(httpInterceptor)
        client.addInterceptor(logging)
        client.addNetworkInterceptor(StethoInterceptor())
        client.cache(cache)
        client.retryOnConnectionFailure(true)
        client.connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        return client.build()
    }

    @Provides
    @Singleton
    fun getApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun nasaDatabase(@ApplicationContext context: Context): NASADatabase =
        Room.databaseBuilder(context, NASADatabase::class.java, Constants.DB_NAME).build()

    @Provides
    @Singleton
    fun nasaDao(NASADatabase: NASADatabase) = NASADatabase.nasaDao()

    @Provides
    @Singleton
    fun getNASARepository(apiService: ApiService): NASARepository {
        return NASARepositoryImpl(apiService)
    }
}