package com.example.quizapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quizSubject: String,
    onQuizFinish: (Int) -> Unit,
    onWrongAnswer: (Quiz, Int) -> Unit
) {
    val context = LocalContext.current
    val soundPlayer = remember { SoundPlayer(context) }

    DisposableEffect(Unit) {
        onDispose {
            soundPlayer.release()
        }
    }

    val quizList = remember { AllQuizzes.filter { it.subject == quizSubject }.shuffled() }

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerChecked by remember { mutableStateOf(false) }

    val currentQuiz = quizList.getOrNull(currentQuestionIndex)
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "$quizSubject 퀴즈") },
                navigationIcon = {
                    IconButton(onClick = { onQuizFinish(score) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (currentQuiz == null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                LaunchedEffect(Unit) {
                    onQuizFinish(score)
                }
                Text(text = "결과 화면으로 이동 중...", fontSize = 24.sp)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "문제 ${currentQuestionIndex + 1} / ${quizList.size}", fontWeight = FontWeight.SemiBold)
                    Text(text = "현재 점수: $score 점", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = currentQuiz.question,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(currentQuiz.options) { index, option ->
                        val isCorrect = isAnswerChecked && index == currentQuiz.answerIndex
                        val isWrongSelected = isAnswerChecked && index == selectedOptionIndex && index != currentQuiz.answerIndex

                        val backgroundColor = when {
                            isCorrect -> Color(0xFFC8E6C9)
                            isWrongSelected -> Color(0xFFFDC2C2)
                            index == selectedOptionIndex -> Color(0xFFBBDEFB)
                            else -> Color.LightGray.copy(alpha = 0.2f)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable(enabled = !isAnswerChecked) {
                                    selectedOptionIndex = index
                                },
                            colors = CardDefaults.cardColors(containerColor = backgroundColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = option,
                                modifier = Modifier.padding(16.dp),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (!isAnswerChecked) {
                    Button(
                        onClick = {
                            if (selectedOptionIndex != null) {
                                isAnswerChecked = true
                                val isCorrect = selectedOptionIndex == currentQuiz.answerIndex

                                if (isCorrect) {
                                    score += 10
                                    soundPlayer.playSound(R.raw.correct_sound)
                                } else {
                                    onWrongAnswer(currentQuiz, selectedOptionIndex!!)
                                    soundPlayer.playSound(R.raw.wrong_sound)
                                }
                            }
                        },
                        enabled = selectedOptionIndex != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("정답 확인", fontSize = 18.sp)
                    }
                } else {
                    Button(
                        onClick = {
                            currentQuestionIndex++
                            selectedOptionIndex = null
                            isAnswerChecked = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("다음 문제", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}