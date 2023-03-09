package com.gmail.zajcevserg.newsdemo.bottom_navigation

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    BottomNavigation {
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