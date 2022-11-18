package com.shivamkumarjha.nasagallery.di

import android.content.Context
import androidx.room.Room
import com.shivamkumarjha.nasagallery.config.Constants
import com.shivamkumarjha.nasagallery.persistence.AppDatabase
import com.shivamkumarjha.nasagallery.repository.DatabaseRepository
import com.shivamkumarjha.nasagallery.repository.DatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDatabaseRepository(appDatabase: AppDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(appDatabase)
    }
}