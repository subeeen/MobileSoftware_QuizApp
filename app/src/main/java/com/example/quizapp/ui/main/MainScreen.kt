package com.example.quizapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Smart Quiz App",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 수도 퀴즈 카드
        TopicCard(
            title = "수도 퀴즈"
        ) {
            navController.navigate("quiz_capitals")
        }

        // IT 상식 퀴즈 카드
        TopicCard(
            title = "IT 상식 퀴즈"
        ) {
            navController.navigate("quiz_it")
        }
    }
}
