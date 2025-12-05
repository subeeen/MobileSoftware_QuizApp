package com.example.quizapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp.data.model.Quiz
import com.example.quizapp.data.model.WrongAnswer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WrongAnswerRepository(context: Context) {
    private val prefs = context.getSharedPreferences("WrongAnswers", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object : TypeToken<List<WrongAnswer>>() {}.type

    private val _wrongAnswers = MutableLiveData<List<WrongAnswer>>()
    val wrongAnswers: LiveData<List<WrongAnswer>> = _wrongAnswers

    init { load() }

    private fun load() {
        val json = prefs.getString("answers", null)
        val list = if (json != null) gson.fromJson<List<WrongAnswer>>(json, type) else emptyList()
        _wrongAnswers.value = list.distinctBy { it.quiz.question }
    }

    fun add(quiz: Quiz, selected: Int) {
        val list = _wrongAnswers.value.orEmpty().toMutableList()
        if (list.none { it.quiz.question == quiz.question }) {
            list.add(WrongAnswer(quiz, selected))
            prefs.edit().putString("answers", gson.toJson(list)).apply()
            _wrongAnswers.value = list
        }
    }

    fun clear() {
        prefs.edit().remove("answers").apply()
        _wrongAnswers.value = emptyList()
    }
}
