package com.example.retobancopichincha.domain.model

data class MealUiModel(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String?,
    var isFavorite: Boolean = false,
    val strTags: String?,
    val strInstructions: String?,
    val ingredients: List<IngredientUiModel>?
)

data class IngredientUiModel(
    val name: String?,
    val measure: String?
)
