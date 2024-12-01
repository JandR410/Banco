package com.example.onboarding.ui.onboarding

import com.example.retobancopichincha.presentation.onboarding.OnboardingAction
import com.example.retobancopichincha.presentation.onboarding.OnboardingEvent
import com.example.retobancopichincha.presentation.onboarding.OnboardingState
import com.example.retobancopichincha.utils.state.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : BaseViewModel<OnboardingState, OnboardingAction, OnboardingEvent>
    (initialState = OnboardingState.buildInitialState())
{
    override fun handleScreenActions(action: OnboardingAction) {
        when (action) {
            OnboardingAction.Navigate -> navigateToAuth()
        }
    }

    private fun navigateToAuth() {
        sendEvent(OnboardingEvent.NavigateAuth)
    }

}