package com.example.retobancopichincha.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.retobancopichincha.R
import com.example.retobancopichincha.domain.model.IngredientUiModel
import com.example.retobancopichincha.domain.model.MealUiModel

@Composable
fun DetailsMeal(
    onFavoriteClick: (MealUiModel) -> Unit,
    meal: MealUiModel?
) {

    meal?.let {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                onClick = {
                    onFavoriteClick(meal)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                IconButton(
                    onClick = {
                        onFavoriteClick(meal)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (meal.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (meal.isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = "Imagen desde internet",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(120.dp)
                    .height(200.dp),
                placeholder = painterResource(R.drawable.comida),
                error = painterResource(R.drawable.comida)
            )

            Text(
                text = meal.strMeal.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            meal.ingredients?.let { list ->
                LazyColumn(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "Ingredients",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                    items(list) {
                        Text(
                            modifier = Modifier.padding(top = 14.dp),
                            text = "• ${it.name.orEmpty()}  ${it.measure.orEmpty()}",
                            textAlign = TextAlign.Center
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "Preparation",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            modifier = Modifier.padding(top = 14.dp),
                            text = meal.strInstructions.orEmpty(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }


        }
    }


}

@Preview(showBackground = true)
@Composable
fun DetailsMoviePreview() {
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
    DetailsMeal(
        onFavoriteClick = {},
        meal
    )
}