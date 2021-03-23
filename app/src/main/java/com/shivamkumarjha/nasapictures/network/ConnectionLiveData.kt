package com.shivamkumarjha.nasapictures.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

class ConnectionLiveData(
    private val connectivityManager: ConnectivityManager,
    private val networkHelper: NetworkHelper
) : LiveData<Boolean>() {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        postValue(false) // default no internet
        if (Build.VERSION.SDK_INT >= 21) {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    postValue(true)
                }

                override fun onLost(network: Network) {
                    postValue(false)
                }
            }
        } else {
            postValue(networkHelper.isNetworkConnected())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else if (Build.VERSION.SDK_INT >= 21) {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }

    override fun onInactive() {
        if (Build.VERSION.SDK_INT >= 21) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
        super.onInactive()
    }
}