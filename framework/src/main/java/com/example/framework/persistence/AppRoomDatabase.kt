package com.example.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DispGuardado::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun dispTVDao(): IDispGuardadoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            // Retorna la instancia si ya existe
            return INSTANCE ?: synchronized(this) {
                // Crea la instancia si es nula
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "disp_guardados_database"
                )
                    // Opcional, solo en desarrollo para no romper por cambios de esquema
                    //.fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}