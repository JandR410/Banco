package com.example.retobancopichincha.presentation.favorite

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenState

data class FavoriteState (
    val meals: List<MealUiModel>
 ):ScreenState {
     companion object {
         fun buildInitialState() = FavoriteState(
             meals = listOf()
         )
     }
}