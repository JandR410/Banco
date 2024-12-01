package com.example.retobancopichincha.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retobancopichincha.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAllMeals(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteMeal(favorite: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE idMeal = :mealId")
    suspend fun deleteMealById(mealId: String)
}