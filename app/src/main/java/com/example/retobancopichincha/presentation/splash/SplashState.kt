package com.example.retobancopichincha.presentation.splash

import com.example.retobancopichincha.utils.base.ScreenState

data class SplashState (
    val duration: Int
) : ScreenState {
    companion object {
        fun initialBuildState() = SplashState(
            duration = 0
        )
    }
}