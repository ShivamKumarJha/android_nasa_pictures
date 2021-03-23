package com.shivamkumarjha.nasapictures.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.repository.NASARepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //TODO remove this, added for testing
    @Inject
    lateinit var nasaRepository: NASARepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            nasaRepository.getNASAData().collect {}
        }
    }
}