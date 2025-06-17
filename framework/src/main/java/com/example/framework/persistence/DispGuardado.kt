package com.example.framework.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "disp_guardado" )
data class DispGuardado(
    @ColumnInfo(name = "nombre_disp")
    var nombre_disp: String,
    @ColumnInfo(name = "token_disp")
    var token_disp: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_disp")
    var id_disp: Long = 0
}