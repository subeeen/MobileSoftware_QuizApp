package com.example.quizapp.ui.wrongnote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.example.quizapp.data.model.WrongAnswer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrongAnswerScreen(
    wrongAnswers: List<WrongAnswer>,
    onClearAll: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("오답 노트", fontWeight = FontWeight.Bold) },
                actions = {
                    if (wrongAnswers.isNotEmpty()) {
                        IconButton(onClick = onClearAll) {
                            Icon(Icons.Default.Delete, contentDescription = "전체 삭제")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (wrongAnswers.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "틀린 문제가 없습니다. \n퀴즈를 풀고 다시 와주세요!",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
            ) {
                items(wrongAnswers) { wrongAnswer ->
                    WrongAnswerItem(wrongAnswer)
                    Divider(Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

@Composable
fun WrongAnswerItem(wrongAnswer: WrongAnswer) {
    val quiz = wrongAnswer.quiz
    val selectedIndex = wrongAnswer.selectedOptionIndex
    val correctIndex = quiz.answerIndex

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "[${quiz.subject}]",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "문제: ${quiz.question}",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            quiz.options.forEachIndexed { index, option ->
                val isCorrect = index == correctIndex
                val isSelectedWrong = index == selectedIndex

                val color = when {
                    isCorrect -> Color(0xFF4CAF50)
                    isSelectedWrong -> Color(0xFFF44336)
                    else -> Color.DarkGray
                }

                val prefix = when {
                    isCorrect -> "정답: "
                    isSelectedWrong -> "오답: "
                    else -> "보기: "
                }

                Text(
                    text = "$prefix$option",
                    color = color,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}