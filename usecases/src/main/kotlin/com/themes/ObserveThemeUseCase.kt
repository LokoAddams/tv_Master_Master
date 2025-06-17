package com.themes

import com.dataStorePreferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class ObserveThemeUseCase (
    private val repository: UserPreferencesRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.isDarkModeFlow
    }
}