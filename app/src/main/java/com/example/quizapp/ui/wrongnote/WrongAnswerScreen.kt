package com.example.quizapp.ui.wrongnote

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "오답 노트",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                },
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
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "틀린 문제가 없습니다!\n퀴즈를 풀고 다시 와주세요 😊",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = 0.dp
                    )
                    .padding(horizontal = 16.dp)
            ) {
                items(wrongAnswers) { wrongAnswer ->
                    WrongAnswerItem(wrongAnswer)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun WrongAnswerItem(wrongAnswer: WrongAnswer) {

    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.97f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    val quiz = wrongAnswer.quiz
    val selectedIndex = wrongAnswer.selectedOptionIndex
    val correctIndex = quiz.answerIndex

    val cardColor = Color(0xFFFFFFFF)

    Card(
        onClick = { pressed = true },
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // 과목 표시
            Text(
                text = "[${quiz.subject}]",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 문제
            Text(
                text = quiz.question,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(0xFF212121)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 보기 리스트
            quiz.options.forEachIndexed { index, option ->
                val isCorrect = index == correctIndex
                val isSelectedWrong = index == selectedIndex

                val color = when {
                    isCorrect -> Color(0xFF8BC34A)
                    isSelectedWrong -> Color(0xFFE57373)
                    else -> Color(0xFF5F6368)
                }

                val label = when {
                    isCorrect -> "정답"
                    isSelectedWrong -> "오답"
                    else -> "보기"
                }

                Text(
                    text = "$label  •  $option",
                    color = color,
                    fontSize = 15.sp,
                    fontWeight = if (isCorrect || isSelectedWrong) FontWeight.SemiBold else FontWeight.Normal,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }
        }
    }
}
