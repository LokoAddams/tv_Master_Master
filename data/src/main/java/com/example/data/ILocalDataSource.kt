package com.example.data

import com.example.domain.DispTV

interface ILocalDataSource {
    suspend fun saveDisp(dispositivo: DispTV): Boolean
}