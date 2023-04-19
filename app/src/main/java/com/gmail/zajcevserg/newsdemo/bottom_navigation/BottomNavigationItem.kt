package com.gmail.zajcevserg.newsdemo.bottom_navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi

@Composable
fun BottomNavItem(
    bottomBarFeatureApi: BottomNavigationApi,
    navHostController: NavHostController,
    currentBottomNavGraphRoute: String,
    rowScope: RowScope
) {
    rowScope.BottomNavigationItem(
        selected = currentBottomNavGraphRoute == bottomBarFeatureApi.bottomNavGraphRoute(),
        icon = { BottomNavIcon(resId = bottomBarFeatureApi.bottomBarIconRes()) },
        label = { BottomNavTitle(resId = bottomBarFeatureApi.bottomBarTitleRes()) },
        onClick = {
            bottomBarFeatureApi.onBottomNavItemClicked(
                navHostController = navHostController,
                currentBottomNavGraphRoute = currentBottomNavGraphRoute
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .testTag("bottom_navigation_bar_${bottomBarFeatureApi.bottomNavGraphRoute()}_test_tag")
    )
}

@Composable
private fun BottomNavIcon(resId: Int) {
    Icon(
        painter = painterResource(id = resId),
        contentDescription = null
    )
}

@Composable
private fun BottomNavTitle(resId: Int) {
    Text(
        text = stringResource(id = resId)
    )
}