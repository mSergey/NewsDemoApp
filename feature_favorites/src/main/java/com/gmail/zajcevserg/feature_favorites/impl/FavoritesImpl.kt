package com.gmail.zajcevserg.feature_favorites.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gmail.zajcevserg.feature_favorites.R
import com.gmail.zajcevserg.feature_favorites.presentation.FavoritesScreen
import com.gmail.zajcevserg.feature_favorites_api.FavoritesApi
import javax.inject.Inject

class FavoritesImpl @Inject constructor(): FavoritesApi {
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

    override fun registerInBottomNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.navigation(
            route = bottomNavGraphRoute(),
            startDestination = bottomNavGraphRoute() + featureRoute()
        ) {
            composable(bottomNavGraphRoute() + featureRoute()) {
                FavoritesScreen(navController = navController)
            }
        }
    }
}