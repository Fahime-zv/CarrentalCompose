package com.example.learningcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.onSimpleCLickListener

@Composable
fun GearButton(text:String,onCLickListener: onSimpleCLickListener){
    Button(
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red), // Customize color
        onClick = { onCLickListener.invoke()},
        modifier = Modifier
            .padding(bottom = 16.dp, end = 10.dp, start = 10.dp).fillMaxWidth()
    ) {
        Text(
            text=text,
            fontSize = 18.sp,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(5.dp)
        )
    }
}