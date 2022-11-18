package com.shivamkumarjha.nasagallery.repository

import android.util.Log
import com.shivamkumarjha.nasagallery.model.CoroutineDispatchers
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.model.Resource
import com.shivamkumarjha.nasagallery.network.ApiService
import com.shivamkumarjha.nasagallery.network.NoConnectivityException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NASARepositoryImpl(
    private val apiService: ApiService,
    private val dispatchers: CoroutineDispatchers,
    private val databaseRepository: DatabaseRepository
) : NASARepository {

    companion object {
        private const val TAG = "NasaRepo"
    }

    override suspend fun getImages(): Flow<Resource<List<NASA>?>> = flow {
        emit(Resource.Loading(data = null))
        try {
            val response = apiService.getImages()
            if (response.isSuccessful) {
                val data = response.body()
                data?.forEach { nasa ->
                    databaseRepository.addImage(nasa)
                }
                emit(Resource.Success(data = data))
            } else {
                emit(Resource.Error(data = null, message = "${response.code()}"))
            }
        } catch (exception: Exception) {
            if (exception is NoConnectivityException) {
                emit(Resource.Offline(data = null))
            } else {
                emit(Resource.Error(data = null, message = exception.message.toString()))
                Log.e(TAG, exception.message.toString())
            }
        }
    }.flowOn(dispatchers.io)
}