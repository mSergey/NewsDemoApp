package com.gmail.zajcevserg.feature_else.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ElseViewModel : ViewModel() {

    private var _counter: MutableState<Int> = mutableStateOf(0)

    val counter: State<Int>
        get() = _counter

    fun increment() {
        _counter.value++
    }
}