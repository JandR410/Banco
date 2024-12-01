package com.example.retobancopichincha.presentation.details

import androidx.lifecycle.viewModelScope
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.usecase.DeleteFavoriteUseCase
import com.example.retobancopichincha.usecase.SetFavoriteUseCase
import com.example.retobancopichincha.utils.state.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel<DetailState, DetailAction, DetailEvent>(initialState = DetailState.buildInitialState()) {

    override fun handleScreenActions(action: DetailAction) {
        when (action) {
            is DetailAction.Favorite -> validateFavorite(action.favorite)
        }
    }

    private fun validateFavorite(mealUiModel: MealUiModel) {
        if (mealUiModel.isFavorite) {
            viewModelScope.launch {
                setFavoriteUseCase.invoke(mealUiModel)
            }
        } else {
            viewModelScope.launch {
                deleteFavoriteUseCase.invoke(mealUiModel)
            }
        }
    }

}