package com.example.retobancopichincha.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Esta es una extensión para acceder al DataStore de preferencias de manera fácil
val Context.dataStore by preferencesDataStore(name = "settings")

// Clave para almacenar si es la primera vez que se abre la app
val FIRST_LAUNCH_KEY = booleanPreferencesKey("first_launch")

// Función para leer si es la primera vez
suspend fun isFirstLaunch(context: Context): Boolean {
    val preferences = context.dataStore.data.first()
    return preferences[FIRST_LAUNCH_KEY] ?: true // Retorna `true` si no se ha guardado nada
}

// Función para marcar que la primera vez ha pasado
suspend fun markFirstLaunchComplete(context: Context) {
    context.dataStore.edit { preferences ->
        preferences[FIRST_LAUNCH_KEY] = false // Marcamos que ya no es el primer lanzamiento
    }
}
