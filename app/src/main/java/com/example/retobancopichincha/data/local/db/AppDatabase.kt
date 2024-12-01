package com.example.retobancopichincha.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retobancopichincha.data.local.dao.FavoriteDao
import com.example.retobancopichincha.data.local.dao.IngredientDao
import com.example.retobancopichincha.data.local.dao.MealDao
import com.example.retobancopichincha.data.local.entity.FavoriteEntity
import com.example.retobancopichincha.data.local.entity.IngredientEntity
import com.example.retobancopichincha.data.local.entity.MealEntity

@Database(entities = [MealEntity::class, IngredientEntity::class, FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun favoriteDao(): FavoriteDao
}
