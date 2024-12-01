package com.example.retobancopichincha.presentation.home

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenState

data class HomeState(
    val meals: List<MealUiModel>,
    val details: MealUiModel?,
    val listFavorite: List<MealUiModel>
) : ScreenState {
    companion object {
        fun buildInitialState() = HomeState(
            meals = listOf(),
            details = null,
            listFavorite = listOf()
        )
    }
}