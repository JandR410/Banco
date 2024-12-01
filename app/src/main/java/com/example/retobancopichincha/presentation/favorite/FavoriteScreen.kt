package com.example.retobancopichincha.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retobancopichincha.design.AppMealItem
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.presentation.home.HomeAction
import com.example.retobancopichincha.presentation.home.HomeEvent
import com.example.retobancopichincha.presentation.home.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun FavoriteScreen(
    screenActionHandler: (HomeAction) -> Unit,
    state: HomeState,
    navigate: (MealUiModel) -> Unit,
    events: SharedFlow<HomeEvent>
) {
    LaunchedEffect(key1 = Unit) {
        events.collect { event ->
            when (event) {
                is HomeEvent.NavigateDetails -> navigate(event.meal)
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(state.listFavorite) {
            AppMealItem(
                onClick = { screenActionHandler(HomeAction.ItemSelect(it)) },
                meal = it,
                onFavoriteClick = { favorite ->
                    screenActionHandler(HomeAction.Favorite(favorite))
                }
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(
        screenActionHandler = {},
        state = HomeState.buildInitialState(),
        navigate = {},
        events = MutableSharedFlow()
    )
}