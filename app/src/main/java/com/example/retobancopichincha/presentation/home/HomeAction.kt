package com.example.retobancopichincha.presentation.home

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenAction

sealed class HomeAction : ScreenAction {
    data class ItemSelect(val mealSelect: MealUiModel) : HomeAction()
    data class Favorite(val favorite: MealUiModel) : HomeAction()
    data class FavoriteDetail(val favorite: MealUiModel) : HomeAction()
}
