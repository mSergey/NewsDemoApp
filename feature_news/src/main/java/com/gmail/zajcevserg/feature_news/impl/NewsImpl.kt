package com.gmail.zajcevserg.feature_news.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gmail.zajcevserg.feature_news.R
import com.gmail.zajcevserg.feature_news.presentation.NewsListScreen
import com.gmail.zajcevserg.feature_news.presentation.NewsListViewModelFactory
import com.gmail.zajcevserg.feature_news_api.NewsApi
import com.gmail.zajcevserg.feature_news_details_api.NewsDetailsApi
import javax.inject.Inject


class NewsImpl @Inject constructor(
    private val viewModelFactory: NewsListViewModelFactory,
    private val newsDetailsApi: NewsDetailsApi
) : NewsApi {

    override fun onBottomNavItemClicked(
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

    override fun bottomBarIconRes(): Int {
        return R.drawable.round_newspaper_24
    }

    override fun bottomBarTitleRes(): Int {
        return R.string.news_list_feature_name
    }

    override fun featureRoute(): String {
        return "list"
    }

    override fun bottomNavGraphRoute(): String {
        return "news/"
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
                NewsListScreen(
                    viewModelFactory = viewModelFactory,
                    onItemClickHandler = {
                        newsDetailsApi.navigate(
                            navHostController = navController,
                            homeRoute = bottomNavGraphRoute(),
                            idArg = it,
                        )
                    }
                )
            }

            newsDetailsApi.registerChildGraph(
                navGraphBuilder = this,
                navController = navController,
                homeRoute = bottomNavGraphRoute(),
                modifier = Modifier
            )
        }
    }
}