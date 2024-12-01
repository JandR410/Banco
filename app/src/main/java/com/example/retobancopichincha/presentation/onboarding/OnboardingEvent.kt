package com.example.retobancopichincha.presentation.onboarding

import com.example.retobancopichincha.utils.base.ScreenEvent

sealed class OnboardingEvent : ScreenEvent {
    data object NavigateAuth : OnboardingEvent()
}