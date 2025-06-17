package com.example.tvmaster.Conexion

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConexionViewModel: ViewModel() {
    private val _conexionExitosa = MutableStateFlow<Boolean?>(null)
    val conexionExitosa: StateFlow<Boolean?> = _conexionExitosa

    fun verificarConexion() {
        val numeroAleatorio = (1..100).random()
        _conexionExitosa.value = numeroAleatorio in 40..100 // Ejemplo: 40+ es "buena"
    }
}