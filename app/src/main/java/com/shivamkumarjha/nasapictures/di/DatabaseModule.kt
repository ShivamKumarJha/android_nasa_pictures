package com.shivamkumarjha.nasapictures.di

import android.content.Context
import androidx.room.Room
import com.shivamkumarjha.nasapictures.config.Constants
import com.shivamkumarjha.nasapictures.persistence.NASADatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun nasaDatabase(@ApplicationContext context: Context): NASADatabase =
        Room.databaseBuilder(context, NASADatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun nasaDao(NASADatabase: NASADatabase) = NASADatabase.nasaDao()
}