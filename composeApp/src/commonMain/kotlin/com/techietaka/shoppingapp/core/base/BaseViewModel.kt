package com.techietaka.shoppingapp.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State, Event, SideEffect> : ViewModel() {

    public var uiState: MutableStateFlow<State> =
        MutableStateFlow(initState())
        private set

    var sideEffectFlow: Channel<SideEffect> = Channel(Channel.CONFLATED)
        private set

    protected abstract fun initState(): State

    protected abstract fun onLoading(isLoading: Boolean)

    public abstract fun onEvent(event: Event)

    abstract fun sendSideEffect(sideEffect: SideEffect)

    protected fun updateState(newState: (currentState: State) -> State) {
        uiState.update { state: State ->
            newState(state)
        }
    }
}