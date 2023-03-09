package com.gmail.zajcevserg.feature_else.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ElseScreen(
    viewModel: ElseViewModel = viewModel(ElseViewModel::class.java)
) {
    val counterState by viewModel.counter
    Column(
        modifier = Modifier.background(Color.LightGray).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Else Screen",
            fontSize = 32.sp
        )
        Text(
            text = "Counter $counterState",
            fontSize = 32.sp,
            modifier = Modifier.clickable {
                viewModel.increment()
            }
        )
    }
}