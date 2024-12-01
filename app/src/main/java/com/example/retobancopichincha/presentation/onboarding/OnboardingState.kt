package com.example.retobancopichincha.presentation.onboarding

import com.example.retobancopichincha.utils.base.ScreenState

data class OnboardingState(
    val initial: Boolean
) : ScreenState {
    companion object {
        fun buildInitialState() = OnboardingState(
            initial = false
        )
    }
}