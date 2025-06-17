package com.example.usecases

import com.example.data.DispGuardadosRepository

class existeDispTV(
    private val repository: DispGuardadosRepository
) {
    suspend operator fun invoke(friendlyName: String): Boolean {
        return repository.existeDispTV(friendlyName)
    }
}