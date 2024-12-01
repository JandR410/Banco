package com.example.retobancopichincha.presentation.details

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.utils.base.ScreenState

data class DetailState (
    val mealUiModel: MealUiModel?
): ScreenState {
    companion object {
        fun buildInitialState () = DetailState (
            mealUiModel = null
        )
    }
}