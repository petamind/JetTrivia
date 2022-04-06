package com.petamind.example.jettrivia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petamind.example.jettrivia.model.TriviaItem
import com.petamind.example.jettrivia.repository.TriviaRepository
import com.squareup.moshi.Types
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(private val triviaRepo: TriviaRepository): ViewModel(){
    //private var _triviaData: MutableLiveData<List<TriviaItem>> = MutableLiveData()
    var triviaData = MutableLiveData<List<TriviaItem>>()

    private val listType = Types.newParameterizedType(
        List::class.java, TriviaItem::class.java
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            triviaData.postValue(triviaRepo.callWebService())
        }
    }
}