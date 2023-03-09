package com.gmail.zajcevserg.newsdemo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi
import com.gmail.zajcevserg.core_api.providers.AppWithFacade
import com.gmail.zajcevserg.feature_else_api.ElseApi
import com.gmail.zajcevserg.feature_favorites_api.FavoritesApi
import com.gmail.zajcevserg.feature_news_api.NewsApi
import com.gmail.zajcevserg.newsdemo.activity.di.DaggerActivityComponent
import com.gmail.zajcevserg.newsdemo.bottom_navigation.AppNavGraph
import com.gmail.zajcevserg.newsdemo.bottom_navigation.BottomBar
import com.gmail.zajcevserg.newsdemo.theme.NewsDemoTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var news: NewsApi

    @Inject
    lateinit var favorites: FavoritesApi

    @Inject
    lateinit var elze: ElseApi


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val facade = (application as AppWithFacade).getFacade()
        DaggerActivityComponent.builder()
            .providersFacade(facade)
            .build()
            .inject(this)

        setContent {
            NewsDemoTheme {
                // A surface container using the 'background' color from the theme
                val features = remember { listOf<BottomNavigationApi>(news, favorites, elze) }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        bottomBar = {
                            BottomBar(
                                navController = navController,
                                bottomBarFeatures = features
                            )
                        }
                    ) { contentPadding ->
                        AppNavGraph(
                            featuresApi = features,
                            navController = navController,
                            modifier = Modifier.padding(contentPadding),
                        )
                    }
                }
            }
        }
    }
}
