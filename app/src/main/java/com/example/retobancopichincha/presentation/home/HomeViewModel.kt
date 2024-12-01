package com.example.retobancopichincha.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.usecase.DeleteFavoriteUseCase
import com.example.retobancopichincha.usecase.GetFavoriteUseCase
import com.example.retobancopichincha.usecase.GetMealUseCase
import com.example.retobancopichincha.usecase.SetFavoriteUseCase
import com.example.retobancopichincha.utils.Result
import com.example.retobancopichincha.utils.state.BaseViewModel
import com.example.retobancopichincha.utils.state.ButtonState
import com.example.retobancopichincha.utils.state.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel<HomeState, HomeAction, HomeEvent>(initialState = HomeState.buildInitialState()) {

    private val _meals = mutableStateOf<List<MealUiModel>>(emptyList())
    val meals: State<List<MealUiModel>> = _meals

    override fun handleScreenActions(action: HomeAction) {
        when (action) {
            is HomeAction.ItemSelect -> {
                select(action.mealSelect)
            }

            is HomeAction.Favorite -> updateState(action.favorite)
            is HomeAction.FavoriteDetail -> updateState(action.favorite)
        }
    }

    init {
        favoriteMeal()
        loadMeals()
    }

    private fun select(meal: MealUiModel) {
        setState(state.copy(details = meal))
        favoriteMeal()
        sendEvent(HomeEvent.NavigateDetails(meal))
    }

    private fun favoriteMeal() {
        viewModelScope.launch {
            when (val result = getFavoriteUseCase.invoke()) {
                is Result.Success -> {
                    setState(
                        state.copy(
                            listFavorite = result.data
                        )
                    )
                }

                is Result.Failure -> {
                }
            }
        }
    }

    private fun updateState(meal: MealUiModel) {
        val index = state.meals.indexOfFirst { it.idMeal == meal.idMeal }

        val updatedMeals: MutableList<MealUiModel> = if (index != -1) {
            state.meals.toMutableList().apply {
                this[index] = state.meals[index].copy(isFavorite = !meal.isFavorite)
            }
        } else {
            state.meals.toMutableList()
        }

        setState(state.copy(meals = updatedMeals))
        setState(state.copy(details = state.meals[index]))
        validateFavorite(index)
    }

    private fun validateFavorite(index: Int) {
        if (state.meals[index].isFavorite) {
            viewModelScope.launch {
                setFavoriteUseCase.invoke(state.meals[index])
            }
        } else {
            viewModelScope.launch {
                deleteFavoriteUseCase.invoke(state.meals[index])
            }
        }
        favoriteMeal()
    }

    private fun loadMeals() {
        showLoading()

        viewModelScope.launch {
            when (val result = getMealUseCase.invoke()) {
                is Result.Success -> {
                    hideLoading()
                    val mealsResult = result.data
                    val updatedMeals = mealsResult.map { meal ->
                        val favorite = state.listFavorite.find { it.idMeal == meal.idMeal }
                        meal.copy(isFavorite = favorite?.isFavorite ?: false)

                    }
                    _meals.value = updatedMeals

                    setState(
                        state.copy(
                            meals = meals.value
                        )
                    )

                }

                is Result.Failure -> {
                    hideLoading()
                    dialogError()
                }
            }
        }
    }

    private fun dialogError() {
        showDialog(
            DialogState(
                title = "Error",
                message = "El servicio no responde correctamente",
                firstButton = ButtonState(
                    text = "Volver a intentar",
                    onButtonClicked = {
                        loadMeals()
                        dismissDialog()
                    }
                ),
                secondButton = ButtonState(
                    text = "Usar los datos locales",
                    onButtonClicked = {
                        dismissDialog()
                    }
                )
            )
        )

    }
}