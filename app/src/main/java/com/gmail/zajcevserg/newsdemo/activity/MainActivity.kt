package com.gmail.zajcevserg.newsdemo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gmail.zajcevserg.core_api.navigation.BottomNavigationApi
import com.gmail.zajcevserg.core_api.providers.AppWithFacade
import com.gmail.zajcevserg.feature_favorites_api.FavoritesApi
import com.gmail.zajcevserg.feature_news_api.NewsApi
import com.gmail.zajcevserg.newsdemo.activity.di.DaggerActivityComponent
import com.gmail.zajcevserg.newsdemo.bottom_navigation.AppNavGraph
import com.gmail.zajcevserg.newsdemo.bottom_navigation.BottomBar
import com.gmail.zajcevserg.newsdemo.theme.NewsDemoTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var news: NewsApi

    @Inject
    lateinit var favorites: FavoritesApi

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
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
                val features = remember { listOf<BottomNavigationApi>(news, favorites) }
                val navController = rememberAnimatedNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val bottomVisibilityState = remember { mutableStateOf(true) }
                    LaunchedEffect(key1 = Unit) {
                        navController.addOnDestinationChangedListener { controller, destination, arguments ->
                            val isBottomAppBarVisibility =
                                destination.route!!.contains("details").not()
                            bottomVisibilityState.value = isBottomAppBarVisibility
                        }
                    }
                    Scaffold (
                        bottomBar = {
                            if (bottomVisibilityState.value) {
                                BottomBar(
                                    navController = navController,
                                    bottomBarFeatures = features
                                )
                            }
                        }
                    ) { contentPadding ->
                        AppNavGraph(
                            featuresApi = features,
                            navController = navController,
                            modifier = Modifier.padding(contentPadding)
                        )
                    }
                }
            }
        }
    }
}
