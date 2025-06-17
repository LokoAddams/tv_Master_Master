package com.example.tvmaster.Settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.themes.ObserveThemeUseCase
import com.themes.SetThemeUseCase
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val observeTheme: ObserveThemeUseCase,
    private val setTheme: SetThemeUseCase
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = observeTheme()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    fun changeTheme(enabled: Boolean) {
        viewModelScope.launch {
            setTheme(enabled)
        }
    }
}