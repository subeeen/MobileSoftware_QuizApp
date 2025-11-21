package com.example.quizapp.ui.main

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onQuizStart: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "모바일 소프트웨어 퀴즈",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "퀴즈 주제를 선택하세요",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "도전하고 싶은 분야를 골라볼까요?",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(40.dp))

            QuizCategoryCard(
                title = "과학 상식",
                emoji = "🔬",
                onClick = { onQuizStart("과학 상식") },
                containerColor = Color(0xFFE8EAF6)
            )

            Spacer(modifier = Modifier.height(16.dp))

            QuizCategoryCard(
                title = "스포츠 상식",
                emoji = "⚽",
                onClick = { onQuizStart("스포츠 상식") },
                containerColor = Color(0xFFFCE4EC)
            )


            Spacer(modifier = Modifier.height(16.dp))

            // ---------------- 수도 상식 ----------------
            QuizCategoryCard(
                title = "수도 상식",
                emoji = "🌍",
                onClick = { onQuizStart("수도 상식") },
                containerColor = Color(0xFFE3F2FD)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ----------------  IT 상식 ----------------
            QuizCategoryCard(
                title = "IT 상식",
                emoji = "💻",
                onClick = { onQuizStart("IT 상식") },
                containerColor = Color(0xFFE8F5E9)
            )
        }
    }
}

@Composable
fun QuizCategoryCard(
    title: String,
    emoji: String,
    onClick: () -> Unit,
    containerColor: Color
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Card(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .scale(scale),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                fontSize = 36.sp,
                modifier = Modifier.padding(end = 18.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F1F1F)
            )
        }
    }
}