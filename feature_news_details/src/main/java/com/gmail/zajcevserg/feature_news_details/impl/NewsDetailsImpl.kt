package com.gmail.zajcevserg.feature_news_details.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gmail.zajcevserg.feature_news_details.presentation.NewsDetailsScreen
import com.gmail.zajcevserg.feature_news_details.presentation.NewsDetailsViewModelFactory
import com.gmail.zajcevserg.feature_news_details_api.NewsDetailsApi
import javax.inject.Inject

private const val ARG_NAME = "details_id"
class NewsDetailsImpl @Inject constructor(
    private val detailsViewModelFactory: NewsDetailsViewModelFactory
) : NewsDetailsApi {

    override fun navigate(
        navHostController: NavHostController,
        homeRoute: String,
        idArg: Long
    ) {
        navHostController.navigate(
            route = homeRoute + featureRoute() + idArg
        )
    }

    override fun registerChildGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        homeRoute: String,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = homeRoute + featureRoute() + "{$ARG_NAME}",
            arguments = listOf(
                navArgument(ARG_NAME) { type = NavType.LongType }
            )
        ) {
            NewsDetailsScreen(
                viewModelFactory = detailsViewModelFactory,
                navController = navController,
                detailsId = it.arguments?.getLong(ARG_NAME) ?: 0L,
                modifier = modifier,
            )
        }
    }

    override fun featureRoute(): String {
        return "details/"
    }
}