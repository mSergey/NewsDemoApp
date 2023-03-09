package com.gmail.zajcevserg.newsdemo.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi


@Composable
fun AppNavGraph(
    navController: NavHostController,
    featuresApi: List<BottomNavigationApi>,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = featuresApi.first().bottomNavGraphRoute()
    ) {

        featuresApi.forEach { feature ->
            feature.registerInBottomNavGraph(
                navGraphBuilder = this,
                navController = navController
            )
        }
    }
}