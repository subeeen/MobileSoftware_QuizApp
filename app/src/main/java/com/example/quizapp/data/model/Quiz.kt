package com.example.quizapp.data.model

data class Quiz(
    val subject: String,
    val question: String,
    val options: List<String>,
    val answerIndex: Int
)
