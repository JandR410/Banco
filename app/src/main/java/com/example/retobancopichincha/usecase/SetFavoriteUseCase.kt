package com.example.retobancopichincha.usecase

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.domain.repository.MealRepository
import com.example.retobancopichincha.utils.Result
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(
    private val mealResult: MealRepository
) {
    suspend operator fun invoke(mealUiModel: MealUiModel): Result<Boolean> {
        return try {
            mealResult.insertFavorite(mealUiModel = mealUiModel)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}