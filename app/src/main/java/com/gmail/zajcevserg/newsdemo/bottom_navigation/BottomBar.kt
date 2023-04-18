package com.gmail.zajcevserg.newsdemo.bottom_navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi


@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarFeatures: List<BottomNavigationApi>,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: bottomBarFeatures.first().bottomNavGraphRoute()
    BottomNavigation(
        modifier = Modifier,
        elevation = 2.dp
    ) {
        bottomBarFeatures.forEach {
            BottomNavItem(
                bottomBarFeatureApi = it,
                navHostController = navController,
                currentBottomNavGraphRoute = currentRoute,
                rowScope = this
            )
        }
    }
}