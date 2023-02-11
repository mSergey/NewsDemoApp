package com.gmail.zajcevserg.feature_news_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gmail.zajcevserg.features_shared.data.NewsRepository
import com.gmail.zajcevserg.features_shared.data.NewsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    init {
        Log.d("myLog", "NewsListViewModel init ${hashCode()}")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchNews().forEach {
                    Log.d("myLog", it.description)
                }
            }
        }
    }

}

class NewsListViewModelFactory @Inject constructor(
    private val repository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}