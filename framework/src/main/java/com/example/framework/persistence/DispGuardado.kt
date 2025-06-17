package com.example.framework.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "disp_guardados" )
data class DispGuardado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "friendlyName")
    val friendlyName: String
)