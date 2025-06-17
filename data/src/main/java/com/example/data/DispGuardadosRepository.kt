package com.example.data

import com.example.domain.DispTV

class DispGuardadosRepository (
    private val localDataSource: ILocalDataSource
){
    suspend fun saveDisp(dispositivo: DispTV): Boolean {
        this.localDataSource.saveDisp(dispositivo)
        return true
    }
}