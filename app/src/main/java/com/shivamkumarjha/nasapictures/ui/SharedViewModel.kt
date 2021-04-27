package com.shivamkumarjha.nasapictures.ui

import androidx.lifecycle.*
import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.network.Resource
import com.shivamkumarjha.nasapictures.repository.DatabaseRepository
import com.shivamkumarjha.nasapictures.repository.NASARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val nasaRepository: NASARepository
) : ViewModel() {
    private val _nasa = MutableLiveData<Resource<List<NASA>?>>()
    val nasa: LiveData<Resource<List<NASA>?>> = _nasa

    val images = liveData(Dispatchers.IO) {
        emitSource(databaseRepository.getImages())
    }

    val bookmarks = liveData(Dispatchers.IO) {
        emitSource(databaseRepository.getBookmarkedImages())
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            nasaRepository.getNASAData().collect {
                _nasa.postValue(it)
            }
        }
    }

    fun updateBookmark(isBookmarked: Boolean, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.updateBookmark(isBookmarked, url)
        }
    }
}