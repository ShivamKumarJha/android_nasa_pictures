package com.shivamkumarjha.nasapictures.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.shivamkumarjha.nasapictures.databinding.ActivityMainBinding
import com.shivamkumarjha.nasapictures.repository.NASARepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Views
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    //TODO remove this, added for testing
    @Inject
    lateinit var nasaRepository: NASARepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()

        lifecycleScope.launch(Dispatchers.IO) {
            nasaRepository.getNASAData().collect {}
        }
    }

    private fun setViews() {
        //Toolbar
        toolbar = binding.toolbarId
        setSupportActionBar(toolbar)
    }
}