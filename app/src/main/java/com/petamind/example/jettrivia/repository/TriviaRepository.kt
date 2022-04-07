package com.petamind.example.jettrivia.repository

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import com.petamind.example.jettrivia.model.TriviaItem
import com.petamind.example.jettrivia.model.TriviaAPI
import com.petamind.example.jettrivia.util.LOG_TAG
import com.petamind.example.jettrivia.util.NetworkHelper
import com.petamind.example.jettrivia.util.QUIZ_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class TriviaRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: TriviaAPI
) {

    @WorkerThread
    suspend fun callWebService(): List<TriviaItem> {
        if (NetworkHelper.networkAvailable(context = context)) {
            val serviceData = (api.getTriviaData().body() ?: emptyList())
            Log.i(LOG_TAG, "call Web service: called")
            return serviceData
        } else {
            Log.d(LOG_TAG, "callWebService: No Internet")
        }
        return emptyList()
    }
}
