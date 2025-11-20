package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quizapp.ui.main.MainScreen
import com.example.quizapp.ui.ranking.RankingScreen
import com.example.quizapp.ui.wrongnote.WrongNoteScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main") { MainScreen(navController) }
            composable("wrongnote") { WrongNoteScreen() }
            composable("ranking") { RankingScreen() }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("메인", "main"),
        BottomNavItem("오답노트", "wrongnote"),
        BottomNavItem("랭킹", "ranking")
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("main") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = {},
                label = { Text(item.label) }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val route: String
)
