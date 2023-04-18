package com.gmail.zajcevserg.core_api.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface BottomNavigationApi : BaseNavigationApi {
    fun bottomNavGraphRoute(): String
    fun registerInBottomNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
    fun bottomBarIconRes(): Int
    fun bottomBarTitleRes(): Int
    fun onBottomNavItemClicked(
        navHostController: NavHostController,
        currentBottomNavGraphRoute: String
    ) {
        val currentRoute = navHostController.currentDestination?.route!!
        val startGraphRoute = navHostController.graph.findStartDestination().route!!

        if (!currentRoute.startsWith(bottomNavGraphRoute())) {
            navHostController.navigate(bottomNavGraphRoute()) {
                launchSingleTop = true
                restoreState = true
                popUpTo(startGraphRoute) {
                    saveState = true
                }
            }
        }
    }
}