package com.gmail.zajcevserg.newsdemo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gmail.zajcevserg.core_api.providers.AppWithFacade
import com.gmail.zajcevserg.feature_news_list.presentation.NewsListScreen

import com.gmail.zajcevserg.feature_news_list.presentation.NewsListViewModel
import com.gmail.zajcevserg.feature_news_list.presentation.NewsListViewModelFactory
import com.gmail.zajcevserg.newsdemo.activity.di.DaggerActivityComponent
import com.gmail.zajcevserg.newsdemo.theme.NewsDemoTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var newsListViewModelFactory: NewsListViewModelFactory

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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsListScreen(
                        viewModel = viewModel(
                            factory = newsListViewModelFactory
                        )
                    )
                }
            }
        }
    }
}
