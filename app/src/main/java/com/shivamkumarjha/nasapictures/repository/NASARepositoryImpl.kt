package com.shivamkumarjha.nasapictures.repository

import android.util.Log
import com.shivamkumarjha.nasapictures.config.Constants
import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.network.ApiService
import com.shivamkumarjha.nasapictures.network.NoConnectivityException
import com.shivamkumarjha.nasapictures.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NASARepositoryImpl(
    private val apiService: ApiService,
    private val databaseRepository: DatabaseRepository,
) : NASARepository {

    override suspend fun getNASAData(): Flow<Resource<ArrayList<NASA>?>> = flow {
        emit(Resource.loading(data = null))
        try {
            //Get from database
            val dbData = databaseRepository.getData()
            if (!dbData.isNullOrEmpty()) {
                emit(Resource.success(data = dbData))
            }
            val response = apiService.getNASAData()
            if (response.isSuccessful) {
                emit(Resource.success(data = response.body()))
                Log.d(Constants.TAG, response.body().toString())
                //Save to database
                if (!response.body().isNullOrEmpty()) {
                    databaseRepository.addData(response.body()!!)
                }
            } else {
                emit(Resource.error(data = null, message = response.code().toString()))
                Log.d(Constants.TAG, response.code().toString())
            }
        } catch (exception: Exception) {
            if (exception is NoConnectivityException)
                emit(Resource.offline(data = null))
            else {
                emit(Resource.error(data = null, message = exception.message.toString()))
                Log.e(Constants.TAG, exception.message.toString())
            }
        }
    }.flowOn(Dispatchers.IO)
}