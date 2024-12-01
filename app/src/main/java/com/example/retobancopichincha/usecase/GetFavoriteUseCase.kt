package com.example.retobancopichincha.usecase

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.domain.repository.MealRepository
import com.example.retobancopichincha.utils.Result
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val mealResult: MealRepository
) {
    suspend operator fun invoke(): Result<List<MealUiModel>> {
        return try {
            val favorite = mealResult.getFavorite()
            Result.Success(favorite)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}