package com.gmail.zajcevserg.feature_news_details.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun NewsDetailsScreen(
    viewModelFactory: NewsDetailsViewModelFactory,
    navController: NavHostController,
    detailsId: Long,
    modifier: Modifier = Modifier
) {
    val vm = viewModel(
        modelClass = NewsDetailsViewModel::class.java,
        factory = viewModelFactory
    )

    val state by vm.observeUiState().collectAsState()
    vm.sendActionToGetNewState(detailsId)
    if (!state.isInitial) {
        Text("Hello, news app!!! ${state.detailsUiModel.content}")
    }

}