package com.example.hypergol.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WikiScreen(){
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Wiki",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.Black

        )
    }
}

@Composable
@Preview
fun WikiScreenPreview(){
    WikiScreen()
}