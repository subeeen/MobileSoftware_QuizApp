package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quizapp.data.repository.ScoreRepository
import com.example.quizapp.data.repository.WrongAnswerRepository
import com.example.quizapp.ui.main.MainScreen
import com.example.quizapp.ui.quiz.QuizScreen
import com.example.quizapp.ui.result.ResultScreen
import com.example.quizapp.ui.ranking.RankingScreen
import com.example.quizapp.ui.wrongnote.WrongAnswerScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    scoreRepository: ScoreRepository,
    wrongAnswerRepository: WrongAnswerRepository,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {

        composable("main") {
            MainScreen(
                onQuizStart = { subject ->
                    navController.navigate("quiz/$subject")
                }
            )
        }

        composable(
            route = "quiz/{subject}",
            arguments = listOf(navArgument("subject") { type = NavType.StringType })
        ) { entry ->
            val subject = entry.arguments?.getString("subject") ?: ""

            QuizScreen(
                quizSubject = subject,
                onQuizFinish = { score ->
                    scoreRepository.save(score)
                    navController.navigate("result/$subject/$score")
                },
                onWrongAnswer = { quiz, index ->
                    wrongAnswerRepository.add(quiz, index)
                }
            )
        }

        composable(
            route = "result/{subject}/{score}",
            arguments = listOf(
                navArgument("subject") { type = NavType.StringType },
                navArgument("score") { type = NavType.IntType }
            )
        ) { entry ->
            val subject = entry.arguments?.getString("subject") ?: ""
            val score = entry.arguments?.getInt("score") ?: 0

            ResultScreen(
                quizSubject = subject,
                finalScore = score,
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("main") { inclusive = false }
                    }
                },
                onNavigateToRanking = {
                    navController.navigate("ranking")
                },
                onNavigateToWrongNote = {
                    navController.navigate("wrong_note")
                }
            )
        }

        composable("ranking") {
            RankingScreen(
                rankings = scoreRepository.rankings.value ?: emptyList(),
                onClearRankings = { scoreRepository.clear() }
            )
        }

        composable("wrong_note") {
            WrongAnswerScreen(
                wrongAnswers = wrongAnswerRepository.wrongAnswers.value ?: emptyList(),
                onClearAll = { wrongAnswerRepository.clear() }
            )
        }
    }
}
