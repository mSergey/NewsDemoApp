package com.gmail.zajcevserg.feature_news.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.gmail.zajcevserg.feature_news.domain.ArticleUiModel

@Composable
fun NewsListScreen(
    viewModelFactory: NewsListViewModelFactory,
    onItemClickHandler: (idArg: Long) -> Unit
) {

    val vm = viewModel(
        modelClass = NewsListViewModel::class.java,
        factory = viewModelFactory
    )
    val uiState by vm.observeUiState().collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = uiState.articles,
            key = { it.id }
        ) {
            Article(
                articleModel = it,
                actionHandler = onItemClickHandler
            )
        }
    }
}

@Composable
fun Article(
    articleModel: ArticleUiModel,
    actionHandler: (idArg: Long) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .clickable { actionHandler.invoke(articleModel.id) }
    ) {
        AsyncImage(
            model = articleModel.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = articleModel.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Text(
            text = articleModel.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
        )

    }
}