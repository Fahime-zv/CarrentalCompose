package com.example.learningcompose.ui.screen.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun OrderScreen(
) {
    Text(modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, text = "Orders", fontSize = 44.sp)

}