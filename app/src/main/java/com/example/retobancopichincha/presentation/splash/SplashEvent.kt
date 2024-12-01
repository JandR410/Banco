package com.example.retobancopichincha.presentation.splash

import com.example.retobancopichincha.utils.base.ScreenEvent

sealed class SplashEvent : ScreenEvent {
    data class Navigation(val navigation: String) : SplashEvent()
}