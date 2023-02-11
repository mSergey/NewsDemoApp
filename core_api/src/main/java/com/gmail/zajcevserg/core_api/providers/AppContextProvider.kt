package com.gmail.zajcevserg.core_api.providers

import android.content.Context

interface AppContextProvider {
    fun provideAppContext(): Context
}