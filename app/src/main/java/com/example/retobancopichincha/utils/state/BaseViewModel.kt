package com.example.retobancopichincha.utils.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retobancopichincha.utils.base.ScreenAction
import com.example.retobancopichincha.utils.base.ScreenEvent
import com.example.retobancopichincha.utils.base.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ScreenState, Action : ScreenAction, Event : ScreenEvent>(
    initialState: State
) : ViewModel(), StateHandler<State> by StateHandlerImpl(initialState) {

    abstract fun handleScreenActions(action: Action)

    private val _eventsFlow = MutableSharedFlow<Event>()
    val eventsFlow: SharedFlow<Event>
        get() = _eventsFlow


    protected fun sendEvent(event: Event) {
        viewModelScope.launch {
            _eventsFlow.emit(event)
        }
    }
}



