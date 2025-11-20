package com.example.quizapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onQuizStart: (String) -> Unit,
    onNavigateToRanking: () -> Unit,
    onNavigateToWrongAnswers: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("모바일 소프트웨어 퀴즈") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "퀴즈 과목을 선택하세요",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            QuizSubjectButton(
                text = "과학 상식",
                onClick = { onQuizStart("과학 상식") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            QuizSubjectButton(
                text = "스포츠 상식",
                onClick = { onQuizStart("스포츠 상식") }
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SmallButton(
                    text = "랭킹 보기",
                    onClick = onNavigateToRanking,
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                SmallButton(
                    text = "오답 노트",
                    onClick = onNavigateToWrongAnswers, // 추가된 파라미터를 사용합니다.
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun QuizSubjectButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text, fontSize = 20.sp)
    }
}

@Composable
fun SmallButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Text(text)
    }
}