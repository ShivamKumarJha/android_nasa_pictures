package com.shivamkumarjha.nasapictures.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivamkumarjha.nasapictures.model.NASA
import com.shivamkumarjha.nasapictures.network.Resource
import com.shivamkumarjha.nasapictures.repository.NASARepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel @ViewModelInject constructor(
    private val nasaRepository: NASARepository
) : ViewModel() {
    val nasa = MutableLiveData<Resource<List<NASA>?>>()

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            nasaRepository.getNASAData().collect {
                nasa.postValue(it)
            }
        }
    }
}