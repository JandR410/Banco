package com.example.retobancopichincha.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.retobancopichincha.design.DetailsMeal
import com.example.retobancopichincha.domain.model.IngredientUiModel
import com.example.retobancopichincha.domain.model.MealUiModel
import com.example.retobancopichincha.presentation.home.HomeAction
import com.example.retobancopichincha.presentation.home.HomeState

@Composable
fun DetailScreen(
    screenActionHandler: (HomeAction) -> Unit,
    state: HomeState,
) {
    DetailsMeal(
        onFavoriteClick = { screenActionHandler(HomeAction.FavoriteDetail(it)) },
        meal = state.details
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val meal = MealUiModel(
        idMeal = "1",
        strMeal = "Spaghetti",
        strMealThumb = "https://image.tmdb.org/t/p/w500/meal.jpg",
        isFavorite = true,
        strInstructions = "To make the pastry, measure the flour into a bowl and rub in the butter with your fingertips until the mixture resembles fine breadcrumbs. Add the water, mixing to form a soft dough.\\r\\nRoll out the dough on a lightly floured work surface and use to line a 20cm\\/8in flan tin. Leave in the fridge to chill for 30 minutes.\\r\\nPreheat the oven to 200C\\/400F\\/Gas 6 (180C fan).\\r\\nLine the pastry case with foil and fill with baking beans. Bake blind for about 15 minutes, then remove the beans and foil and cook for a further five minutes to dry out the base.\\r\\nFor the filing, spread the base of the flan generously with raspberry jam.\\r\\nMelt the butter in a pan, take off the heat and then stir in the sugar. Add ground almonds, egg and almond extract. Pour into the flan tin and sprinkle over the flaked almonds.\\r\\nBake for about 35 minutes. If the almonds seem to be browning too quickly, cover the tart loosely with foil to prevent them burning.",
        strTags = "Tart,Baking,Alcoholic",
        ingredients = listOf(
            IngredientUiModel("Tomato", "1 cup"),
            IngredientUiModel("Spaghetti", "200g")
        )
    )
    DetailScreen(
        screenActionHandler = {},
        state = HomeState.buildInitialState()
    )
}