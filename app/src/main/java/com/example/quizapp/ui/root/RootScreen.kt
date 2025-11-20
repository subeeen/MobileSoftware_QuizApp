package com.example.quizapp.ui.root

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.navigation.NavGraph
import com.example.quizapp.data.repository.ScoreRepository
import com.example.quizapp.data.repository.WrongAnswerRepository
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.List

@Composable
fun RootScreen(
    scoreRepository: ScoreRepository,
    wrongAnswerRepository: WrongAnswerRepository
) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "main",
                    onClick = { navController.navigate("main") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("메인") }
                )

                NavigationBarItem(
                    selected = currentRoute == "ranking",
                    onClick = { navController.navigate("ranking") },
                    icon = { Icon(Icons.Default.Star, contentDescription = null) },
                    label = { Text("랭킹") }
                )

                NavigationBarItem(
                    selected = currentRoute == "wrong_note",
                    onClick = { navController.navigate("wrong_note") },
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("오답노트") }
                )
            }
        }
    ) { padding ->
        NavGraph(
            navController = navController,
            scoreRepository = scoreRepository,
            wrongAnswerRepository = wrongAnswerRepository,
            modifier = Modifier.padding(padding)
        )
    }
}
