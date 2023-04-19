package com.gmail.zajcevserg.newsdemo.bottom_navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    featuresApi: List<BottomNavigationApi>,
    modifier: Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = featuresApi.first().bottomNavGraphRoute(),
        modifier = Modifier.then(modifier)
    ) {
        featuresApi.forEach { feature ->
            feature.registerInBottomNavGraph(
                navGraphBuilder = this,
                navController = navController
            )
        }
    }
}