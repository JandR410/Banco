package com.example.retobancopichincha.presentation.home

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenEvent

sealed class HomeEvent : ScreenEvent {
    data class NavigateDetails(val meal: MealUiModel) : HomeEvent()
}