package com.example.usecases

import com.example.data.DispGuardadosRepository
import com.example.domain.DispTV

class SaveDispTV (
    val repository: DispGuardadosRepository
){
    suspend fun invoke(dispositivo: DispTV) {
        repository.saveDisp(dispositivo)
    }
}