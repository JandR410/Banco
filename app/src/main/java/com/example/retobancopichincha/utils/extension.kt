package com.example.retobancopichincha.utils

import com.example.retobancopichincha.data.local.entity.FavoriteEntity
import com.example.retobancopichincha.data.local.entity.IngredientEntity
import com.example.retobancopichincha.data.local.entity.MealEntity
import com.example.retobancopichincha.data.model.response.Meal
import com.example.retobancopichincha.domain.model.IngredientUiModel
import com.example.retobancopichincha.domain.model.MealUiModel

fun MealEntity.toUiModel(ingredients: List<IngredientEntity>): MealUiModel {
    return MealUiModel(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        isFavorite = isFavorite,
        strTags = strTags,
        strInstructions = strInstructions,
        ingredients = ingredients.map {
            IngredientUiModel(
                name = it.name,
                measure = it.measure
            )
        }
    )
}


fun getIngredientsFromMeal(meal: Meal): List<IngredientUiModel> {
    val ingredients = mutableListOf<IngredientUiModel>()
    for (i in 1..20) {
        val ingredientName = meal.javaClass.getDeclaredField("strIngredient$i").get(meal) as? String
        val ingredientMeasure = meal.javaClass.getDeclaredField("strMeasure$i").get(meal) as? String
        if (ingredientName != null && ingredientMeasure != null) {
            ingredients.add(IngredientUiModel(ingredientName, ingredientMeasure))
        }
    }
    return ingredients
}

fun MealEntity.toUiModel(isFavorite: Boolean = false): MealUiModel {
    val ingredients = listOf<IngredientUiModel>()

    return MealUiModel(
        idMeal = this.idMeal,
        strMeal = this.strMeal,
        strMealThumb = this.strMealThumb,
        isFavorite = this.isFavorite,
        strTags = this.strTags,
        strInstructions = this.strInstructions,
        ingredients = ingredients
    )
}

fun FavoriteEntity.toUiModel(): MealUiModel {
    val ingredients = listOf<IngredientUiModel>()

    return MealUiModel(
        idMeal = this.idMeal,
        strMeal = this.strMeal,
        strMealThumb = this.strMealThumb,
        isFavorite = this.isFavorite,
        strTags = this.strTags,
        strInstructions = this.strInstructions,
        ingredients = ingredients
    )
}

fun Meal.toIngredientEntities(): List<IngredientEntity> {
    val ingredients = mutableListOf<IngredientEntity>()
    for (i in 1..20) {
        val ingredientName =
            this::class.java.getDeclaredField("strIngredient$i").apply { isAccessible = true }
                .get(this) as? String
        val ingredientMeasure =
            this::class.java.getDeclaredField("strMeasure$i").apply { isAccessible = true }
                .get(this) as? String
        if (!ingredientName.isNullOrEmpty()) {
            ingredients.add(
                IngredientEntity(
                    mealId = idMeal,
                    name = ingredientName,
                    measure = ingredientMeasure
                )
            )
        }
    }
    return ingredients
}
