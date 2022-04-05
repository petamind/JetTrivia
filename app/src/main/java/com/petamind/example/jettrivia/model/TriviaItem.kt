package com.petamind.example.jettrivia.model

data class TriviaItem(
    val answer: String,
    val category: String,
    val choices: List<String>,
    val question: String
)