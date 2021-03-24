package com.shivamkumarjha.nasapictures

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.shivamkumarjha.nasapictures.ui.BaseApplication

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?, className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, BaseApplication::class.java.name, context)
    }
}