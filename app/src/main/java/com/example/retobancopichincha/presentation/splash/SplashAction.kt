package com.example.retobancopichincha.presentation.splash

import android.content.Context
import com.example.retobancopichincha.utils.base.ScreenAction

sealed class SplashAction: ScreenAction {
    data class Navigation(val context: Context): SplashAction()
}