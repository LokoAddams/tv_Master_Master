package com.example.framework.dispGuardados

import android.content.Context
import com.example.data.ILocalDataSource
import com.example.domain.DispTV
import com.example.framework.mappers.toEntity
import com.example.framework.persistence.AppRoomDatabase
import com.example.framework.persistence.IDispGuardadoDAO
import com.example.framework.mappers.toDomain

class DispGuardadosLocalDataSource (
    val context: Context
) : ILocalDataSource {
    val dispGuardadoDAO: IDispGuardadoDAO = AppRoomDatabase.getDatabase(context).dispTVDao()
    override suspend fun saveDisp(dispositivo: DispTV): Boolean {
        dispGuardadoDAO.insert(dispositivo.toEntity())
        return true
    }
    override suspend fun getDispsGuardados(): List<DispTV> {
        val entities = dispGuardadoDAO.getAllDisps()
        return entities.map { it.toDomain() }
    }

    override suspend fun existeDispTV(friendlyName: String): Boolean {
        val count = dispGuardadoDAO.countByFriendlyName(friendlyName)
        return count > 0
    }
}