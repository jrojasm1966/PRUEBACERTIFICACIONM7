package com.example.pruebacertificacion.Modelo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebacertificacion.Modelo.local.PlantasDao
import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.local.entities.PlantasEntity


@Database(entities = [PlantasEntity:: class,PlantasDetailEntity::class], version = 1,
exportSchema = false)
abstract class PlantasDataBase: RoomDatabase() {

    // referencia del dao
    abstract fun getPlantasDao(): PlantasDao

    companion object{

        @Volatile
        private var
                INSTANCE : PlantasDataBase? = null
        fun getDataBase(context: Context) : PlantasDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantasDataBase::class.java, "Plantas")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}