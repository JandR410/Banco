package com.example.retobancopichincha.di

import android.content.Context
import androidx.room.Room
import com.example.retobancopichincha.data.local.dao.FavoriteDao
import com.example.retobancopichincha.data.local.dao.IngredientDao
import com.example.retobancopichincha.data.local.dao.MealDao
import com.example.retobancopichincha.data.local.db.AppDatabase
import com.example.retobancopichincha.data.remote.MealApi
import com.example.retobancopichincha.data.repository.MealRepositoryImpl
import com.example.retobancopichincha.domain.repository.MealRepository
import com.example.retobancopichincha.usecase.GetMealUseCase
import com.example.retobancopichincha.utils.createUnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4:3001/")
            .client(createUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealApiService(retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "meal_database"
        ).build()
    }

    @Provides
    fun provideMealDao(database: AppDatabase): MealDao {
        return database.mealDao()
    }

    @Provides
    fun provideIngredientDao(appDatabase: AppDatabase): IngredientDao {
        return appDatabase.ingredientDao()
    }

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideMealRepository(
        mealApiService: MealApi,
        mealDao: MealDao,
        ingredientDao: IngredientDao,
        favoriteDao: FavoriteDao
    ): MealRepository = MealRepositoryImpl(mealApiService, mealDao, ingredientDao, favoriteDao)

    @Provides
    @Singleton
    fun provideGetMealUseCase(mealRepositoryImpl: MealRepositoryImpl): GetMealUseCase {
        return GetMealUseCase(mealRepositoryImpl)
    }
}

