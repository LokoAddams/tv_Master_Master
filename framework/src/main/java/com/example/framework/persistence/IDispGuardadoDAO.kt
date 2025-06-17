package com.example.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IDispGuardadoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dispositivo: DispGuardado)  // Cambiado a DispGuardado

    @Query("SELECT * FROM disp_guardados")
    suspend fun getAllDisps(): List<DispGuardado>

    @Query("SELECT COUNT(*) FROM disp_guardados WHERE friendlyName = :friendlyName")
    suspend fun countByFriendlyName(friendlyName: String): Int
}