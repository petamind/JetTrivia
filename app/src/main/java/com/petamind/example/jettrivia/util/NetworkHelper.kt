package com.petamind.example.jettrivia.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext

object NetworkHelper {
    fun networkAvailable(@ApplicationContext context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        Log.i(LOG_TAG, "networkAvailable: called")
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}