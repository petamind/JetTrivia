package com.petamind.example.jettrivia.repository

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.petamind.example.jettrivia.model.TriviaItem
import com.petamind.example.jettrivia.model.TriviaService
import com.petamind.example.jettrivia.util.LOG_TAG
import com.petamind.example.jettrivia.util.NetworkHelper
import com.petamind.example.jettrivia.util.QUIZ_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class TriviaRepository @Inject constructor(@ApplicationContext private val context: Context) {


    @WorkerThread
    suspend fun callWebService(): List<TriviaItem> {
        if(NetworkHelper.networkAvailable(context = context)){
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder().baseUrl(QUIZ_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            val service = retrofit.create(TriviaService::class.java)
            val serviceData = (service.getTriviaData().body()?: emptyList()).also {
                it.forEach {
                    Log.i(LOG_TAG, "Data received: ${it.answer}")
                }
            }
            Log.i(LOG_TAG, "call Web service: called")
            //triviaData.postValue(serviceData)
            return serviceData
        }
        return emptyList()
    }
//    fun getTriviaData(context: Context){
//        val text = ""
//        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//        val adapter: JsonAdapter<List<TriviaItem>> = moshi.adapter(listType)
//        triviaData.value = adapter.fromJson(text) ?: emptyList()
//    }
}
