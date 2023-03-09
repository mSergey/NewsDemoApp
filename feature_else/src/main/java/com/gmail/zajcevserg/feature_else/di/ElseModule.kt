package com.gmail.zajcevserg.feature_else.di

import com.gmail.zajcevserg.feature_else.impl.ElseImpl
import com.gmail.zajcevserg.feature_else_api.ElseApi
import dagger.Binds
import dagger.Module

@Module
interface ElseModule {

    @Binds
    fun bindElse(
        elseImpl: ElseImpl
    ): ElseApi
}