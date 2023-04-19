package com.gmail.zajcevserg.feature_favorites.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.gmail.zajcevserg.feature_favorites.R
import com.gmail.zajcevserg.feature_favorites.domain.FavoritesArticleUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(
    vmFactory: FavoritesViewModelFactory,
    navController: NavHostController
) {

    val vm = viewModel(
        modelClass = FavoritesViewModel::class.java,
        factory = vmFactory
    )

    val uiState by vm.observeUiState().collectAsState()

    DisposableEffect(key1 = Unit) {
        vm.sendAction(FavoritesAction.ActionGet)
        onDispose {  }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        modifier = Modifier
            .fillMaxHeight()
            .testTag("favorites_list_test_tag")
    ) {
        items(
            items = uiState.favoritesArticles,
            key = { it.id }
        ) {
            FavoritesArticle(
                articleModel = it,
                onDeleteClickHandler = { id ->
                    vm.sendAction(FavoritesAction.ActionDelete(id))
                },
                modifier = Modifier.animateItemPlacement()
            )
        }
    }

}

@Composable
fun FavoritesArticle(
    articleModel: FavoritesArticleUiModel,
    onDeleteClickHandler: (Long) -> Unit,
    modifier: Modifier
) {
    ElevatedCard(
        modifier = Modifier.then(modifier)
    ) {
        Box {
            val colorUnderTitle =
                if (isSystemInDarkTheme()) Color(0xBC000000)
                else Color(0xDAFFFFFF)
            AsyncImage(
                model = articleModel.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(256.dp)
                    .fillMaxWidth()
                    .blur(
                        radiusX = 4.dp,
                        radiusY = 4.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                    )
            )
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier
                    .height(256.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            0.0f to Color.Transparent,
                            0.5f to colorUnderTitle
                        )
                    )

            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    Text(
                        text = articleModel.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = articleModel.sourceName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(0.7f)
                        )
                        IconButton(
                            onClick = { onDeleteClickHandler.invoke(articleModel.id) },
                            modifier = Modifier.weight(0.15f)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = null
                            )
                    }
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.weight(0.15f)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_launch_24),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}