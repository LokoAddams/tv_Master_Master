package com.example.tvmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tvmaster.Settings.SettingsViewModel

import com.example.tvmaster.navigation.AppNavigation
import com.example.tvmaster.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ucbtest)
        super.onCreate(savedInstanceState)
    //algo añadido para que la app ocupe toda la pantalla
    // https://developer.android.com/jetpack/compose/edge-to-edge
        enableEdgeToEdge()
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            var isDarkTheme = viewModel.isDarkTheme.collectAsState().value

            AppTheme(darkTheme = isDarkTheme) {
                AppNavigation(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { viewModel.changeTheme(it) }
                )
            }
        }


    }
}