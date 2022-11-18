package com.shivamkumarjha.nasagallery.ui.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.shivamkumarjha.nasagallery.ui.main.viewmodel.MainViewModel
import com.shivamkumarjha.nasagallery.ui.theme.NASAGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            NASAGalleryTheme {
                MainNavHost(viewModel)
            }
        }
    }
}