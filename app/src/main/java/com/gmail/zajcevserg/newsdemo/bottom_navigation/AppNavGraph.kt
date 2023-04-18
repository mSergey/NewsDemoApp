package com.gmail.zajcevserg.newsdemo.bottom_navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi
import com.google.accompanist.navigation.animation.AnimatedNavHost

//import com.google.accompanist.navigation.animation.AnimatedNavHost


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