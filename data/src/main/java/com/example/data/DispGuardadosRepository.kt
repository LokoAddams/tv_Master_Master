package com.example.data

import com.example.domain.DispTV

class DispGuardadosRepository (
    private val localDataSource: ILocalDataSource
){
    suspend fun saveDisp(dispositivo: DispTV): Boolean {
        this.localDataSource.saveDisp(dispositivo)
        return true
    }
    suspend fun getDispsGuardados(): List<DispTV> {
        return localDataSource.getDispsGuardados()
    }

    suspend fun existeDispTV(friendlyName: String): Boolean {
        return localDataSource.existeDispTV(friendlyName)
    }
}