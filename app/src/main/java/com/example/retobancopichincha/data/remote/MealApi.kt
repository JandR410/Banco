package com.example.retobancopichincha.data.remote

import com.example.retobancopichincha.data.model.response.MealResponse
import retrofit2.http.GET

interface MealApi {
    @GET("meals")
    suspend fun getMeals(): MealResponse
}