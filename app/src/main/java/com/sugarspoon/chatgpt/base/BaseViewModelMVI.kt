package com.sugarspoon.chatgpt.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T : ScreenState, in E : ScreenEvent>(initialVal: T) : ViewModel() {

    private val _state: MutableStateFlow<T> = MutableStateFlow(initialVal)

    val state: StateFlow<T>
        get() = _state

    fun emitEvent(event: E) {
        reduce(_state.value, event)
    }

    fun createNewState(newState: T) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: T, event: E)
}

interface ScreenState

interface ScreenEvent
