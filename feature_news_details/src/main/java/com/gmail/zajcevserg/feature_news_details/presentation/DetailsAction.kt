package com.gmail.zajcevserg.feature_news_details.presentation

sealed class DetailsAction {
    data class ActionGetDetails(val id: Long): DetailsAction()
    object ActionFavoritesButtonPressed : DetailsAction()
}