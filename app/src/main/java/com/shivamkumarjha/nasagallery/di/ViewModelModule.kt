package com.shivamkumarjha.nasagallery.di

import com.shivamkumarjha.nasagallery.model.CoroutineDispatchers
import com.shivamkumarjha.nasagallery.network.ApiService
import com.shivamkumarjha.nasagallery.repository.DatabaseRepository
import com.shivamkumarjha.nasagallery.repository.NASARepository
import com.shivamkumarjha.nasagallery.repository.NASARepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(
        apiService: ApiService,
        dispatchers: CoroutineDispatchers,
        databaseRepository: DatabaseRepository
    ): NASARepository {
        return NASARepositoryImpl(apiService, dispatchers, databaseRepository)
    }
}