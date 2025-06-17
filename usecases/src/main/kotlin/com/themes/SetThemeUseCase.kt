package com.themes

import com.dataStorePreferences.UserPreferencesRepository

class SetThemeUseCase (
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setDarkMode(enabled)
    }
}