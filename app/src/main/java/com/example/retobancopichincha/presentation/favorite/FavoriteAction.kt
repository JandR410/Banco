package com.example.retobancopichincha.presentation.favorite

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenAction

sealed class FavoriteAction : ScreenAction {
    data class ItemSelect(val mealSelect: MealUiModel) : FavoriteAction()
    data class Favorite(val favorite: MealUiModel) : FavoriteAction()
}