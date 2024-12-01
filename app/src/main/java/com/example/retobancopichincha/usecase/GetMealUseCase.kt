package com.example.retobancopichincha.usecase

import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.domain.repository.MealRepository
import com.example.retobancopichincha.utils.Result
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val mealResult: MealRepository
) {
    suspend operator fun invoke(): Result<List<MealUiModel>> {
        return try {
            val meals = mealResult.getMealsFromApi()
            Result.Success(meals)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}