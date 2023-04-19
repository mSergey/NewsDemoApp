package com.gmail.zajcevserg.feature_news_details.presentation

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(
    viewModelFactory: NewsDetailsViewModelFactory,
    detailsId: Long,
    onBackClickHandler: () -> Boolean
) {
    val vm: NewsDetailsViewModel = viewModel(
        modelClass = NewsDetailsViewModel::class.java,
        factory = viewModelFactory
    )
    val uiState by vm.observeUiState().collectAsState()

    LaunchedEffect(key1 = Unit) {
        vm.sendAction(DetailsAction.ActionGetDetails(detailsId))
    }

    if (!uiState.isInitial) {

        val contentScrollState = rememberScrollState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Back to list",
                            fontWeight = FontWeight.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onBackClickHandler.invoke() },
                            modifier = Modifier.testTag("back_button_test_tag")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { vm.sendAction(DetailsAction.ActionFavoritesButtonPressed) },
                            modifier = Modifier.testTag("add_to_favorites_icon_test_tag")

                        ) {
                            val icon = if (uiState.isInFavorites) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Filled.FavoriteBorder
                            }
                            Icon(
                                imageVector = icon,
                                tint = Color.Red,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.shadow(4.dp)
                )
            }
        ) { contentPadding ->
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    DetailsPortrait(
                        contentPadding = contentPadding,
                        contentScrollState = contentScrollState,
                        uiState = uiState
                    )
                }
                else -> {
                    DetailsLandscape(
                        contentPadding = contentPadding,
                        contentScrollState = contentScrollState,
                        uiState = uiState
                    )
                }
            }
        }
    }
}




@Composable
fun DetailsPortrait(
    contentPadding: PaddingValues,
    contentScrollState: ScrollState,
    uiState: DetailsUiState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(contentPadding)
            .verticalScroll(contentScrollState)
    ) {
        AsyncImage(
            model = uiState.detailsUiModel.urlToImage,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = uiState.detailsUiModel.title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = uiState.detailsUiModel.description,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Text(
            text = uiState.detailsUiModel.source,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Text(
            text = uiState.detailsUiModel.publishedAt.toFormattedDate() ?: "No date",
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(alignment = Alignment.End)
        )

        Text(
            text = uiState.detailsUiModel.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = uiState.detailsUiModel.author,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsLandscape(
    contentPadding: PaddingValues,
    contentScrollState: ScrollState,
    uiState: DetailsUiState
) {
    Row {
        AsyncImage(
            model = uiState.detailsUiModel.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f)
                .padding(contentPadding)
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.01f)
                .padding(contentPadding)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.6f)
                .padding(contentPadding)
                .verticalScroll(contentScrollState)
        ) {
            Text(
                text = uiState.detailsUiModel.title,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = uiState.detailsUiModel.description,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Text(
                text = uiState.detailsUiModel.source,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Text(
                text = uiState.detailsUiModel.publishedAt.toFormattedDate() ?: "No date",
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(alignment = Alignment.End)
            )

            Text(
                text = uiState.detailsUiModel.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = uiState.detailsUiModel.author,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }

    }
}
private fun String.toFormattedDate(): String? {
    val inputStringPattern = "yyyy-MM-dd'T'HH:mm:ss"
    val outputStringPattern = "yyyy, d MMM"
    val inputFormatter = SimpleDateFormat(inputStringPattern, Locale.getDefault())
    val inputDate = inputFormatter.parse(this)
    val outputFormatter = SimpleDateFormat(outputStringPattern, Locale.getDefault())
    return inputDate?.let { outputFormatter.format(it) }
}