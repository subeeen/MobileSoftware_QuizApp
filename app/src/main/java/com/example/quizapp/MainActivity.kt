package com.example.quizapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizapp.ui.theme.QuizAppTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var scoreRepository: ScoreRepository
    private lateinit var wrongAnswerRepository: WrongAnswerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scoreRepository = ScoreRepository(this)
        wrongAnswerRepository = WrongAnswerRepository(this)

        setContent {
            QuizAppTheme {
                QuizAppNavigation(
                    scoreRepository = scoreRepository,
                    wrongAnswerRepository = wrongAnswerRepository
                )
            }
        }
    }
}

data class RankItem(
    val score: Int,
    val date: String
)

class ScoreRepository(context: Context) {
    private val prefs = context.getSharedPreferences("QuizScores", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object : TypeToken<List<RankItem>>() {}.type

    private val _rankings = MutableLiveData<List<RankItem>>()
    val rankings: LiveData<List<RankItem>> = _rankings

    init {
        loadScores()
    }

    private fun loadScores() {
        val json = prefs.getString("scores", null)
        val list: List<RankItem> = if (json == null) {
            emptyList()
        } else {
            gson.fromJson(json, type)
        }
        _rankings.value = list.sortedByDescending { it.score }
    }

    fun saveNewScore(score: Int) {
        val currentList = _rankings.value.orEmpty().toMutableList()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val dateString = dateFormat.format(Date())

        val newRankItem = RankItem(score = score, date = dateString)
        currentList.add(newRankItem)

        val sortedList = currentList.sortedByDescending { it.score }

        val topTen = sortedList.take(10)

        val json = gson.toJson(topTen)
        prefs.edit().putString("scores", json).apply()

        _rankings.value = topTen
    }

    fun clearRankings() {
        prefs.edit().remove("scores").apply()
        _rankings.value = emptyList()
    }
}

class WrongAnswerRepository(context: Context) {
    private val prefs = context.getSharedPreferences("WrongAnswers", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object : TypeToken<List<WrongAnswer>>() {}.type

    private val _wrongAnswers = MutableLiveData<List<WrongAnswer>>()
    val wrongAnswers: LiveData<List<WrongAnswer>> = _wrongAnswers

    init {
        loadWrongAnswers()
    }

    private fun loadWrongAnswers() {
        val json = prefs.getString("answers", null)
        val list: List<WrongAnswer> = if (json == null) {
            emptyList()
        } else {
            gson.fromJson(json, type)
        }
        _wrongAnswers.value = list.distinctBy { it.quiz.question }
    }

    fun addWrongAnswer(quiz: Quiz, selectedOptionIndex: Int) {
        val currentList = _wrongAnswers.value.orEmpty().toMutableList()

        if (currentList.none { it.quiz.question == quiz.question }) {
            val newWrongAnswer = WrongAnswer(quiz = quiz, selectedOptionIndex = selectedOptionIndex)
            currentList.add(newWrongAnswer)

            val json = gson.toJson(currentList)
            prefs.edit().putString("answers", json).apply()

            _wrongAnswers.value = currentList
        }
    }

    fun clearWrongAnswers() {
        prefs.edit().remove("answers").apply()
        _wrongAnswers.value = emptyList()
    }
}


object Destinations {
    const val MAIN_SCREEN = "main"
    const val QUIZ_SCREEN = "quiz/{subject}"
    const val RESULT_SCREEN = "result/{subject}/{score}"
    const val RANKING_SCREEN = "ranking"
    const val WRONG_ANSWER_SCREEN = "wrong_answers"
    const val ARG_SUBJECT = "subject"
    const val ARG_SCORE = "score"
}

@Composable
fun QuizAppNavigation(
    scoreRepository: ScoreRepository,
    wrongAnswerRepository: WrongAnswerRepository
) {
    val navController = rememberNavController()
    val currentRankings by scoreRepository.rankings.observeAsState(initial = emptyList())
    val currentWrongAnswers by wrongAnswerRepository.wrongAnswers.observeAsState(initial = emptyList())

    NavHost(navController = navController, startDestination = Destinations.MAIN_SCREEN) {
        composable(Destinations.MAIN_SCREEN) {
            MainScreen(
                onQuizStart = { subject ->
                    navController.navigate("quiz/$subject")
                },
                onNavigateToRanking = {
                    navController.navigate(Destinations.RANKING_SCREEN)
                },
                onNavigateToWrongAnswers = {
                    navController.navigate(Destinations.WRONG_ANSWER_SCREEN)
                }
            )
        }

        composable(
            route = Destinations.QUIZ_SCREEN,
            arguments = listOf(navArgument(Destinations.ARG_SUBJECT) { type = NavType.StringType })
        ) { backStackEntry ->
            val subject = backStackEntry.arguments?.getString(Destinations.ARG_SUBJECT) ?: "과학 상식"
            QuizScreen(
                quizSubject = subject,
                onQuizFinish = { finalScore ->
                    scoreRepository.saveNewScore(finalScore)
                    navController.navigate("result/$subject/$finalScore")
                },
                onWrongAnswer = { quiz, selectedIndex ->
                    wrongAnswerRepository.addWrongAnswer(quiz, selectedIndex)
                }
            )
        }

        composable(
            route = Destinations.RESULT_SCREEN,
            arguments = listOf(
                navArgument(Destinations.ARG_SUBJECT) { type = NavType.StringType },
                navArgument(Destinations.ARG_SCORE) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val subject = backStackEntry.arguments?.getString(Destinations.ARG_SUBJECT) ?: "과학 상식"
            val score = backStackEntry.arguments?.getInt(Destinations.ARG_SCORE) ?: 0

            ResultScreen(
                quizSubject = subject,
                finalScore = score,
                onNavigateToMain = {
                    navController.popBackStack(Destinations.MAIN_SCREEN, inclusive = false)
                },
                onNavigateToRanking = {
                    navController.navigate(Destinations.RANKING_SCREEN)
                }
            )
        }

        composable(Destinations.RANKING_SCREEN) {
            RankingScreen(
                rankings = currentRankings,
                onClearRankings = { scoreRepository.clearRankings() }
            )
        }

        composable(Destinations.WRONG_ANSWER_SCREEN) {
            WrongAnswerScreen(
                wrongAnswers = currentWrongAnswers,
                onClearAll = { wrongAnswerRepository.clearWrongAnswers() }
            )
        }
    }
}