package com.gmail.zajcevserg.feature_favorites.impl

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.gmail.zajcevserg.feature_favorites.R
import com.gmail.zajcevserg.feature_favorites.presentation.FavoritesScreen
import com.gmail.zajcevserg.feature_favorites.presentation.FavoritesViewModelFactory
import com.gmail.zajcevserg.feature_favorites_api.FavoritesApi
import javax.inject.Inject

class FavoritesImpl @Inject constructor(
    private val viewModelFactory: FavoritesViewModelFactory
): FavoritesApi {
    override fun bottomNavGraphRoute(): String {
        return "favorites/"
    }
    override fun bottomBarIconRes(): Int {
        return R.drawable.round_favorite_24
    }
    override fun bottomBarTitleRes(): Int {
        return R.string.favorites_feature_name
    }
    override fun featureRoute(): String {
        return "list"
    }
    @OptIn(ExperimentalAnimationApi::class)
    override fun registerInBottomNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.navigation(
            route = bottomNavGraphRoute(),
            startDestination = bottomNavGraphRoute() + featureRoute(),
        ) {
            composable(
                route = bottomNavGraphRoute() + featureRoute(),
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    )
                }
            ) {
                FavoritesScreen(
                    vmFactory = viewModelFactory,
                    navController = navController
                )
            }
        }
    }
}