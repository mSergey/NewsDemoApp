package com.gmail.zajcevserg.feature_news.presentation

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.gmail.zajcevserg.feature_news.domain.ArticleUiModel

@OptIn(ExperimentalMaterial3Api::class)
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
    LaunchedEffect(key1 = Unit) {
        vm.sendAction(action = NewsListAction.ActionRefresh)
    }

    val snackBarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }

    if (uiState.noInternetConnection) {
        LaunchedEffect(key1 = Unit) {
            snackBarHostState.showSnackbar(
                message = "No internet connection"
            )
        }
    } else if (uiState.serverDoNotResponse) {
        LaunchedEffect(key1 = Unit) {
            snackBarHostState.showSnackbar(
                message = "Server do not response"
            )
        }
    }

    val lazyListState = rememberLazyListState()

    if (uiState.isInitial) return
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                Snackbar(
                    snackbarData = it
                )
            }
        }
    ) { contentPaddings ->
        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                NewsListPortrait(
                    uiState = uiState,
                    contentPaddings = contentPaddings,
                    onItemClickHandler = onItemClickHandler,
                    lazyListState = lazyListState
                )
            }
            else -> {
                NewsListLandscape(
                    uiState = uiState,
                    contentPaddings = contentPaddings,
                    onItemClickHandler = onItemClickHandler,
                    lazyListState = lazyListState
                )
            }
        }
    }
}

@Composable
fun NewsListPortrait(
    uiState: NewsListUiState,
    contentPaddings: PaddingValues,
    onItemClickHandler: (idArg: Long) -> Unit,
    lazyListState: LazyListState
) {
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = contentPaddings.calculateTopPadding() + 16.dp ,
            end = 16.dp,
            bottom = contentPaddings.calculateBottomPadding() + 16.dp
        ),
        modifier = Modifier
            .fillMaxHeight()
            .testTag("news_list_test_tag")
    ) {
        items(
            items = uiState.articles,
            key = { it.id }
        ) {articleUiModel: ArticleUiModel ->
            ArticlePortrait(
                articleModel = articleUiModel,
                onItemClickHandler = onItemClickHandler
            )
        }
    }
}

@Composable
fun NewsListLandscape(
    uiState: NewsListUiState,
    contentPaddings: PaddingValues,
    onItemClickHandler: (idArg: Long) -> Unit,
    lazyListState: LazyListState
) {
    LazyRow(
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = contentPaddings.calculateTopPadding() + 16.dp ,
            end = 16.dp,
            bottom = contentPaddings.calculateBottomPadding() + 16.dp
        ),
        modifier = Modifier
            .fillMaxHeight()
            .testTag("news_list_test_tag")
    ) {
        items(
            items = uiState.articles,
            key = { it.id }
        ) {articleUiModel: ArticleUiModel ->
            ArticleLandscape(
                articleModel = articleUiModel,
                onItemClickHandler = onItemClickHandler
            )
        }
    }
}

@Composable
fun ArticlePortrait(
    articleModel: ArticleUiModel,
    onItemClickHandler: (idArg: Long) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .clickable { onItemClickHandler.invoke(articleModel.id) }
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
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Text(
            text = "Source: ${articleModel.sourceName}",
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        )
    }
}

@Composable
fun ArticleLandscape(
    articleModel: ArticleUiModel,
    onItemClickHandler: (idArg: Long) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .clickable { onItemClickHandler.invoke(articleModel.id) }
            .width(320.dp)
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = articleModel.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )

        Text(
            text = articleModel.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .weight(0.3f)
        )

        Text(
            text = "Source: ${articleModel.sourceName}",
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
                .weight(0.2f)
        )
    }
}