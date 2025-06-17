package com.example.framework.dispGuardados

import android.content.Context
import com.example.data.ILocalDataSource
import com.example.domain.DispTV
import com.example.framework.mappers.toEntity
import com.example.framework.persistence.AppRoomDatabase
import com.example.framework.persistence.IDispGuardadoDAO

class DispGuardadosLocalDataSource (
    val context: Context
) : ILocalDataSource {
    val dispGuardadoDAO: IDispGuardadoDAO = AppRoomDatabase.getDatabase(context).dispTVDao()
    override suspend fun saveDisp(dispositivo: DispTV): Boolean {
        dispGuardadoDAO.insert(dispositivo.toEntity())
        return true
    }
}