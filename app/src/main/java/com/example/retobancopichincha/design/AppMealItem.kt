package com.example.retobancopichincha.design

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.retobancopichincha.R
import com.example.retobancopichincha.domain.model.IngredientUiModel
import com.example.retobancopichincha.domain.model.MealUiModel

@Composable
fun AppMealItem(
    meal: MealUiModel,
    onClick: (MealUiModel) -> Unit,
    onFavoriteClick: (MealUiModel) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(14.dp)
            .clickable { onClick(meal) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RoundedCornerShape(14.dp))
        ) {
            Row(modifier = Modifier.padding(14.dp)) {
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(meal.strMealThumb)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagen de la comida",
                        modifier = Modifier.size(100.dp),
                        placeholder = painterResource(R.drawable.comida),
                        error = painterResource(R.drawable.comida)
                    )
                }
                Row(
                    modifier = Modifier.weight(5f)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(4f)
                            .align(Alignment.CenterVertically)
                            .padding(start = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = meal.strMeal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Instructions: " + meal.strInstructions?.take(30) + if ((meal.strInstructions?.length
                                    ?: 0) > 30
                            ) "..." else "",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )


                    }
                    IconButton(
                        onClick = {
                            onFavoriteClick(meal)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp, end = 8.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = if (meal.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (meal.isFavorite) Color.Red else Color.Gray
                        )
                    }
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealListPreview() {
    val favoriteMeals = MealUiModel(
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
    AppMealItem(
        meal = favoriteMeals,
        onClick = { },
        onFavoriteClick = { }
    )
}



