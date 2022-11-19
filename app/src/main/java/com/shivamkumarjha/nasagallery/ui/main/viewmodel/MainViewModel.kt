package com.shivamkumarjha.nasagallery.ui.main.viewmodel

import androidx.lifecycle.*
import com.shivamkumarjha.nasagallery.model.CoroutineDispatchers
import com.shivamkumarjha.nasagallery.model.NASA
import com.shivamkumarjha.nasagallery.model.Resource
import com.shivamkumarjha.nasagallery.repository.DatabaseRepository
import com.shivamkumarjha.nasagallery.repository.NASARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val nasaRepository: NASARepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {

    val imagesDb = liveData(dispatchers.io) {
        emitSource(databaseRepository.getImages())
    }

    private val _imagesResponse by lazy { MutableLiveData<Resource<List<NASA>?>?>(null) }
    val imagesResponse: LiveData<Resource<List<NASA>?>?> by lazy { _imagesResponse }

    init {
        callImages()
    }

    fun callImages() {
        viewModelScope.launch(dispatchers.io) {
            nasaRepository.getImages().collect {
                _imagesResponse.postValue(it)
            }
        }
    }
}