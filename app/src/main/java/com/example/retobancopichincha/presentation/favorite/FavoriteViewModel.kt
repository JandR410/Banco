package com.example.retobancopichincha.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.usecase.DeleteFavoriteUseCase
import com.example.retobancopichincha.usecase.GetFavoriteUseCase
import com.example.retobancopichincha.usecase.SetFavoriteUseCase
import com.example.retobancopichincha.utils.Result
import com.example.retobancopichincha.utils.state.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel<FavoriteState, FavoriteAction, FavoriteEvent>(initialState = FavoriteState.buildInitialState()) {

    private val _meals = mutableStateOf<List<MealUiModel>>(emptyList())
    val meals: State<List<MealUiModel>> = _meals

    init {
        loadMeals()
    }

    override fun handleScreenActions(action: FavoriteAction) {
        TODO("Not yet implemented")
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
    }

    private fun loadMeals() {
        showLoading()
        viewModelScope.launch {
            when (val result = getFavoriteUseCase.invoke()) {
                is Result.Success -> {
                    hideLoading()
                    val mealsResult = result.data

                    _meals.value = mealsResult

                    setState(
                        state.copy(
                            meals = meals.value
                        )
                    )

                }

                is Result.Failure -> {
                    hideLoading()
                }
            }
        }
    }

}