package com.gmail.zajcevserg.feature_favorites.presentation

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
import androidx.navigation.NavHostController

@Composable
fun FavoritesScreen (
    viewModel: FavoritesViewModel = viewModel(FavoritesViewModel::class.java),
    navController: NavHostController
) {
    val counterState by viewModel.counter
    Column(
        modifier = Modifier.background(Color.LightGray).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Favorites Screen",
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