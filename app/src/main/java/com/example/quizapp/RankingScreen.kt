package com.example.quizapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
data class RankItem(
    val rank: Int,
    val score: Int,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingScreen() {
    val sampleRanks = listOf(
        RankItem(1, 150, "2025/11/17"),
        RankItem(2, 120, "2025/11/15"),
        RankItem(3, 90, "2025/11/10"),
        RankItem(4, 75, "2025/11/08"),
        RankItem(5, 50, "2025/11/05") //임시
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🏆 최고 기록 랭킹") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(sampleRanks) { item ->
                RankItemCard(item)
            }
        }
    }
}

@Composable
fun RankItemCard(item: RankItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.rank}위",
                fontSize = 20.sp,
                modifier = Modifier.width(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "점수: ${item.score}점", fontSize = 16.sp, style = MaterialTheme.typography.titleMedium)
                Text(text = "날짜: ${item.date}", fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRankingScreen() {
    RankingScreen()
}