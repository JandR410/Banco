package com.example.retobancopichincha.usecase

import com.example.retobancopichincha.domain.model.IngredientUiModel
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.domain.repository.MealRepository
import com.example.retobancopichincha.utils.Result
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetFavoriteUseCaseTest {

    @Mock
    private lateinit var mealRepository: MealRepository

    private lateinit var getFavoriteUseCase: GetFavoriteUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getFavoriteUseCase = GetFavoriteUseCase(mealRepository)
    }

    @Test
    fun `given meal repository returns favorites, when invoke use case, then success result`() = runBlocking {

        val ingredients = listOf(
            IngredientUiModel(name = "Chicken", measure = "200g"),
            IngredientUiModel(name = "Spices", measure = "1 tsp")
        )

        val favoriteMeals = listOf(
            MealUiModel(
                idMeal = "1",
                strMeal = "Meal 1",
                strMealThumb = "https://example.com/meal1.jpg",
                isFavorite = true,
                strTags = "Tag1, Tag2",
                strInstructions = "Cook well.",
                ingredients = ingredients
            ),
            MealUiModel(
                idMeal = "2",
                strMeal = "Meal 2",
                strMealThumb = "https://example.com/meal2.jpg",
                isFavorite = true,
                strTags = "Tag3, Tag4",
                strInstructions = "Grill the meat.",
                ingredients = ingredients
            )
        )

        Mockito.`when`(mealRepository.getFavorite()).thenReturn(favoriteMeals)

        val result = getFavoriteUseCase.invoke()

        assert(result is Result.Success)
        val successResult = result as Result.Success
        assertEquals(favoriteMeals, successResult.data)

        assertTrue(successResult.data.isNotEmpty())
        assertEquals("Meal 1", successResult.data[0].strMeal)
        assertEquals(ingredients, successResult.data[0].ingredients)
    }

    @Test
    fun `given meal repository throws exception, when invoke use case, then failure result`() = runBlocking {
        val exception = RuntimeException("Error fetching favorites")
        Mockito.`when`(mealRepository.getFavorite()).thenThrow(exception)

        val result = getFavoriteUseCase.invoke()

        assert(result is Result.Failure)
        val failureResult = result as Result.Failure
        assertEquals(exception, failureResult.exception)
    }

}