package com.gmail.zajcevserg.feature_news_details.impl

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
//import androidx.navigation.compose.composable
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

    @OptIn(ExperimentalAnimationApi::class)
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
            ),
            enterTransition = {
                val isTransitionInsideSubgraph =
                    initialState.destination.route!!.startsWith(
                        prefix = homeRoute
                    )
                if (isTransitionInsideSubgraph) {
                    fadeIn()
                } else {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    )
                }
            },
            exitTransition = {
                val isTransitionInsideSubgraph =
                    targetState.destination.route!!.startsWith(
                        prefix = homeRoute
                    )
                if (isTransitionInsideSubgraph) {
                    fadeOut()
                } else {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    )
                }
            }

        ) {
            NewsDetailsScreen(
                viewModelFactory = detailsViewModelFactory,
                detailsId = it.arguments?.getLong(ARG_NAME) ?: 0L,
                onBackClickHandler = { navController.navigateUp() }
            )
        }
    }

    override fun featureRoute(): String {
        return "details/"
    }
}