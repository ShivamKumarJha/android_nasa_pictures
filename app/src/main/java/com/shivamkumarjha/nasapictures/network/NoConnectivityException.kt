package com.shivamkumarjha.nasapictures.network

import android.content.Context
import com.shivamkumarjha.nasapictures.R
import java.io.IOException

class NoConnectivityException(context: Context) : IOException() {
    override val message: String = context.resources.getString(R.string.no_internet_connection)
}