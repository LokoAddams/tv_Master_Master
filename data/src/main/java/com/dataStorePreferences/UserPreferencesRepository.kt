package com.dataStorePreferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val isDarkModeFlow: Flow<Boolean>
    suspend fun setDarkMode(enabled: Boolean)
}