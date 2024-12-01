package com.example.retobancopichincha.presentation.onboarding

import com.example.retobancopichincha.utils.base.ScreenAction

sealed class OnboardingAction : ScreenAction {
    data object Navigate : OnboardingAction()
}