package com.example.learningcompose.ui.screen.explore

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ExploreScreen(
) {
    Text(modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, text = "Explore", fontSize = 44.sp)
}