package com.example.quizapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp.data.model.RankItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class ScoreRepository(context: Context) {
    private val prefs = context.getSharedPreferences("QuizScores", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object : TypeToken<List<RankItem>>() {}.type

    private val _rankings = MutableLiveData<List<RankItem>>()
    val rankings: LiveData<List<RankItem>> = _rankings

    init { loadScores() }

    private fun loadScores() {
        val json = prefs.getString("scores", null)

        val list: List<RankItem> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            emptyList<RankItem>()
        }

        _rankings.value = list.sortedByDescending { it.score }
    }


    fun save(score: Int) {
        val current = _rankings.value.orEmpty().toMutableList()
        val dateString = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())
        current.add(RankItem(score, dateString))

        val sorted = current.sortedByDescending { it.score }.take(10)
        prefs.edit().putString("scores", gson.toJson(sorted)).apply()
        _rankings.value = sorted
    }

    fun clear() {
        prefs.edit().remove("scores").apply()
        _rankings.value = emptyList()
    }
}
