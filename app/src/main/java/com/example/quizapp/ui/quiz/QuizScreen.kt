package com.example.quizapp.ui.quiz

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.R
import com.example.quizapp.data.AllQuizzes
import com.example.quizapp.data.model.Quiz
import com.example.quizapp.ui.sound.SoundPlayer

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
        onDispose { soundPlayer.release() }
    }

    val quizList = remember { AllQuizzes.filter { it.subject == quizSubject }.shuffled() }

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerChecked by remember { mutableStateOf(false) }

    val currentQuiz = quizList.getOrNull(currentQuestionIndex)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "$quizSubject 퀴즈",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                },
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
                Text("결과 화면으로 이동 중...", fontSize = 24.sp)
            }
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                // 문제 번호 & 점수
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "문제 ${currentQuestionIndex + 1} / ${quizList.size}",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "점수: $score",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 문제 텍스트
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF3F6FF)
                    ),
                    elevation = CardDefaults.cardElevation(2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = currentQuiz.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 보기 리스트
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(currentQuiz.options) { index, option ->

                        val isCorrect = isAnswerChecked && index == currentQuiz.answerIndex
                        val isWrong = isAnswerChecked && index == selectedOptionIndex && index != currentQuiz.answerIndex
                        val isSelected = selectedOptionIndex == index

                        val bgColor = when {
                            isCorrect -> Color(0xFFDFF5E3)
                            isWrong -> Color(0xFFF6DADA)
                            isSelected -> Color(0xFFE1EBFF)
                            else -> Color(0xFFF3F6FF)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .scale(
                                    animateFloatAsState(
                                        targetValue = if (selectedOptionIndex == index) 0.97f else 1f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessLow
                                        ),
                                        label = ""
                                    ).value
                                )
                                .padding(vertical = 6.dp)
                                .clickable(enabled = !isAnswerChecked) {
                                    selectedOptionIndex = index
                                },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = bgColor),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Text(
                                text = option,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(18.dp)
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 버튼 영역
                if (!isAnswerChecked) {
                    Button(
                        onClick = {
                            if (selectedOptionIndex != null) {
                                isAnswerChecked = true
                                val correct = selectedOptionIndex == currentQuiz.answerIndex

                                if (correct) {
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
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("정답 확인", fontSize = 18.sp)
                    }
                } else {
                    Button(
                        onClick = {
                            currentQuestionIndex++
                            isAnswerChecked = false
                            selectedOptionIndex = null
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("다음 문제", fontSize = 18.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
