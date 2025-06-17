package com.example.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IDispGuardadoDAO {
    @Query("SELECT * FROM disp_guardado")
    fun getDispGuardados(): List<DispGuardado>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dispAGuardar: DispGuardado)

    @Query("DELETE FROM disp_guardado")
    suspend fun deleteAll()

    @Query("SELECT * FROM disp_guardado WHERE nombre_disp = :nombre_disp LIMIT 1")
    suspend fun findByNameDisp(nombre_disp: String): DispGuardado
}