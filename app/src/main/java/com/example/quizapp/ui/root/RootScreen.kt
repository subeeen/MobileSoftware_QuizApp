package com.example.quizapp.ui.root

import androidx.compose.animation.core.Spring
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.ui.text.font.FontWeight

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

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
            val items = listOf(
                NavItem("main", "메인", Icons.Default.Home),
                NavItem("ranking", "랭킹", Icons.Default.Star),
                NavItem("wrong_note", "오답노트", Icons.Default.List)
            )

            NavigationBar(
                containerColor = Color(0xFFF3F6FF),
                tonalElevation = 0.dp
            ) {
                items.forEach { item ->
                    val selected = currentRoute == item.route

                    val scale by animateFloatAsState(
                        targetValue = if (selected) 1.15f else 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                        label = ""
                    )

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.scale(scale)
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontSize = 11.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF6D8CFF),
                            selectedTextColor = Color(0xFF6D8CFF),
                            indicatorColor = Color(0xFFE0E8FF),
                            unselectedIconColor = Color(0xFF9DA3AE),
                            unselectedTextColor = Color(0xFF9DA3AE)
                        )
                    )
                }
            }
        }


    ) { padding ->
        NavGraph(
            navController = navController,
            scoreRepository = scoreRepository,
            wrongAnswerRepository = wrongAnswerRepository,
            modifier = Modifier.padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding()
            )
        )
    }
}
