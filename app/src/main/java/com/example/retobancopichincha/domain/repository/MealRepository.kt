package com.example.retobancopichincha.domain.repository

import com.example.retobancopichincha.domain.model.MealUiModel

interface MealRepository {

    suspend fun getMealsFromApi(): List<MealUiModel>

    suspend fun getMealsFromDb(): List<MealUiModel>

    suspend fun getFavorite(): List<MealUiModel>

    suspend fun insertFavorite(mealUiModel: MealUiModel)

    suspend fun deleteFavorite(mealUiModel: MealUiModel)
}