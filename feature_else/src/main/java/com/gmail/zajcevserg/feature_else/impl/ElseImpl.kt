package com.gmail.zajcevserg.feature_else.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gmail.zajcevserg.feature_else.R
import com.gmail.zajcevserg.feature_else.presentation.ElseScreen
import com.gmail.zajcevserg.feature_else_api.ElseApi
import javax.inject.Inject

class ElseImpl @Inject constructor() : ElseApi  {
    override fun bottomNavGraphRoute(): String {
        return "else/"
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
                ElseScreen()
            }
        }
    }

    override fun bottomBarIconRes(): Int {
        return R.drawable.baseline_explore_24
    }

    override fun bottomBarTitleRes(): Int {
        return R.string.feature_name
    }

    override fun featureRoute(): String {
        return "list"
    }
}