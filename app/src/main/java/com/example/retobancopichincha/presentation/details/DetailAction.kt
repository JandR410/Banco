package com.example.retobancopichincha.presentation.details

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenAction

sealed class DetailAction : ScreenAction {
    data class Favorite(val favorite: MealUiModel) : DetailAction()
}