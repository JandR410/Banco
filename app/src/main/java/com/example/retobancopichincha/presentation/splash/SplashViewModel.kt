package com.example.retobancopichincha.presentation.splash

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.retobancopichincha.utils.isFirstLaunch
import com.example.retobancopichincha.utils.markFirstLaunchComplete
import com.example.retobancopichincha.utils.state.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<SplashState, SplashAction, SplashEvent>(initialState = SplashState.initialBuildState()) {

    override fun handleScreenActions(action: SplashAction) {
        when(action) {
            is SplashAction.Navigation -> checkFirstLaunch(action.context)
        }
    }

    private fun checkFirstLaunch(context: Context) {
        viewModelScope.launch {

            val isFirstLaunch = isFirstLaunch(context)

            if (isFirstLaunch) {
                navigation("/onboarding")
            } else {
                navigation("/home")
            }

            markFirstLaunchComplete(context)
        }
    }

    private fun navigation(navigation: String) {
        sendEvent(SplashEvent.Navigation(navigation))
    }
}