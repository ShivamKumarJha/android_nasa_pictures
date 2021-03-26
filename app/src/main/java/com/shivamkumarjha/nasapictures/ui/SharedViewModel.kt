package com.shivamkumarjha.nasapictures.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.network.Resource
import com.shivamkumarjha.nasapictures.repository.NASARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val nasaRepository: NASARepository
) : ViewModel() {
    private val _nasa = MutableLiveData<Resource<List<NASA>?>>()
    val nasa: LiveData<Resource<List<NASA>?>> = _nasa

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            nasaRepository.getNASAData().collect {
                _nasa.postValue(it)
            }
        }
    }
}