package com.gmail.zajcevserg.feature_news_details_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.gmail.zajcevserg.core_api.navigation.BaseNavigationApi

interface NewsDetailsApi : BaseNavigationApi {

    fun navigate(
        navHostController: NavHostController,
        homeRoute: String,
        idArg: Long
    )

    fun registerChildGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        homeRoute: String,
        modifier: Modifier
    )

}