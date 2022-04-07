package com.petamind.example.jettrivia

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
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
fun MainScreen(viewModel: TriviaViewModel?) {
    if (viewModel != null) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val trivia: List<TriviaItem>? by viewModel!!.triviaData.observeAsState(null)
            var index by remember {
                mutableStateOf(0)
            }
            val context = LocalContext.current

            var selected by remember { mutableStateOf("Android") }
            val onSelectedChange = { text: String ->
                selected = text
            }

            var radioGroupOptions =
                if (!trivia.isNullOrEmpty()) trivia?.get(index)?.choices else emptyList()
            var question = if (!trivia.isNullOrEmpty()) trivia?.get(index)?.question ?: "" else ""
            Column(Modifier.padding(12.dp).fillMaxWidth()) {
                LinearProgressIndicator(progress = index.toFloat()/(if(!trivia.isNullOrEmpty()) trivia!!.size else 1), modifier = Modifier.height(30.dp).fillMaxWidth()
                    .clip(RoundedCornerShape(corner = CornerSize(8.dp))))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = question, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(16.dp))
                radioGroupOptions?.forEach { text ->
                    Row(Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selected),
                            onClick = { onSelectedChange(text) }
                        )
                        .padding(horizontal = 16.dp)
                    ) {
                        RadioButton(
                            selected = (text == selected),
                            onClick = { onSelectedChange(text) }
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.subtitle1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    index += 1

                }) {
                    Text(text = "Next", style = MaterialTheme.typography.button)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTriviaTheme {
        MainScreen(null)
    }
}