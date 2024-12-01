package com.example.retobancopichincha.data.repository

import com.example.retobancopichincha.data.local.dao.FavoriteDao
import com.example.retobancopichincha.data.local.dao.IngredientDao
import com.example.retobancopichincha.data.local.dao.MealDao
import com.example.retobancopichincha.data.local.entity.FavoriteEntity
import com.example.retobancopichincha.data.local.entity.MealEntity
import com.example.retobancopichincha.data.remote.MealApi
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.domain.repository.MealRepository
import com.example.retobancopichincha.utils.toIngredientEntities
import com.example.retobancopichincha.utils.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApiService: MealApi,
    private val mealDao: MealDao,
    private val ingredientDao: IngredientDao,
    private val favoriteDao: FavoriteDao
) : MealRepository {

    override suspend fun getMealsFromApi(): List<MealUiModel> {
        return withContext(Dispatchers.IO) {
            val mealResponse = mealApiService.getMeals()

            val meals = mealResponse.meals.map { meal ->
                val mealEntity = MealEntity(
                    idMeal = meal.idMeal,
                    strMeal = meal.strMeal,
                    strMealThumb = meal.strMealThumb,
                    strCategory = meal.strCategory,
                    strDrinkAlternate = meal.strDrinkAlternate,
                    strArea = meal.strArea,
                    strInstructions = meal.strInstructions,
                    strTags = meal.strTags,
                    strYoutube = meal.strYoutube,
                    strSource = meal.strSource,
                    strImageSource = meal.strImageSource,
                    strCreativeCommonsConfirmed = meal.strCreativeCommonsConfirmed,
                    dateModified = meal.dateModified,
                    isFavorite = false
                )

                val ingredients = meal.toIngredientEntities()

                Pair(mealEntity, ingredients)
            }

            mealDao.insertMeals(meals.map { it.first })
            meals.forEach { meal ->
                ingredientDao.insertIngredients(meal.second)
            }

            meals.map { (mealEntity, ingredients) ->
                mealEntity.toUiModel(ingredients)
            }
        }
    }

    override suspend fun getMealsFromDb(): List<MealUiModel> {
        return withContext(Dispatchers.IO) {
            val meals = mealDao.getAllMeals()
            meals.map { it.toUiModel() }
        }
    }

    override suspend fun getFavorite(): List<MealUiModel> {
        val meals = favoriteDao.getAllMeals()
        return meals.map { it.toUiModel() }
    }

    override suspend fun insertFavorite(mealUiModel: MealUiModel) {
        val mealEntity = mealDao.searchMealById(mealUiModel.idMeal)
        val mealFavorite = FavoriteEntity(
            idMeal = mealEntity.idMeal,
            strMeal = mealEntity.strMeal,
            strMealThumb = mealEntity.strMealThumb,
            strCategory = mealEntity.strCategory,
            strDrinkAlternate = mealEntity.strDrinkAlternate,
            strArea = mealEntity.strArea,
            strInstructions = mealEntity.strInstructions,
            strTags = mealEntity.strTags,
            strYoutube = mealEntity.strYoutube,
            strSource = mealEntity.strSource,
            strImageSource = mealEntity.strImageSource,
            strCreativeCommonsConfirmed = mealEntity.strCreativeCommonsConfirmed,
            dateModified = mealEntity.dateModified,
            isFavorite = mealUiModel.isFavorite
        )
        favoriteDao.insertMeal(mealFavorite)
    }

    override suspend fun deleteFavorite(mealUiModel: MealUiModel) {
        favoriteDao.deleteMealById(mealUiModel.idMeal)
    }
}
