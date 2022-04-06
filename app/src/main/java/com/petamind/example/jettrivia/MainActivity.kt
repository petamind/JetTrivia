package com.petamind.example.jettrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.petamind.example.jettrivia.model.TriviaItem
import com.petamind.example.jettrivia.ui.theme.JetTriviaTheme
import com.petamind.example.jettrivia.util.LOG_TAG
import com.petamind.example.jettrivia.viewmodel.TriviaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            JetTriviaTheme {
                // A surface container using the 'background' color from the theme
                val triviaViewModel: TriviaViewModel by viewModels()
                MainScreen(viewModel = triviaViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: TriviaViewModel) {
    val trivia : List<TriviaItem>? by viewModel.triviaData.observeAsState(null)
    Log.i(LOG_TAG, "call Main: called")
    //TODO: travia will be an empty list and not update

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        //Text(text = "Hello")
        LazyColumn{
            if(!trivia.isNullOrEmpty())
            items(trivia as List<TriviaItem>){
                Text(text = it.answer)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    JetTriviaTheme {
//        MainScreen("Android")
//    }
//}