package com.shivamkumarjha.nasapictures.di

import com.shivamkumarjha.nasapictures.network.ApiService
import com.shivamkumarjha.nasapictures.persistence.NASADao
import com.shivamkumarjha.nasapictures.repository.DatabaseRepository
import com.shivamkumarjha.nasapictures.repository.DatabaseRepositoryImpl
import com.shivamkumarjha.nasapictures.repository.NASARepository
import com.shivamkumarjha.nasapictures.repository.NASARepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getDatabaseRepository(nasaDao: NASADao): DatabaseRepository {
        return DatabaseRepositoryImpl(nasaDao)
    }

    @Provides
    @Singleton
    fun getNASARepository(
        apiService: ApiService,
        databaseRepository: DatabaseRepository,
    ): NASARepository {
        return NASARepositoryImpl(apiService, databaseRepository)
    }
}