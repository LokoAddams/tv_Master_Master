package com.example.framework.DataStorePref

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dataStorePreferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(
    private val context: Context
) : UserPreferencesRepository {

    companion object {
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
        private val Context.dataStore by preferencesDataStore(name = "settings")
    }

    override val isDarkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { it[DARK_MODE] ?: false }

    override suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { it[DARK_MODE] = enabled }
    }
}