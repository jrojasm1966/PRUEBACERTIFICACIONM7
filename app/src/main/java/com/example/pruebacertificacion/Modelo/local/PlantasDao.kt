package com.example.pruebacertificacion.Modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.local.entities.PlantasEntity

@Dao
interface PlantasDao {

    // insertar lista de Plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlantas(listPlantas: List<PlantasEntity>)

    // seleccionar Listado de Plantas
    @Query("SELECT * FROM plantas_list_table ORDER BY id ASC")
    fun getAllPlantas(): LiveData<List<PlantasEntity>>

    // inserta de a 1 Planta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlantasDetail(planta: PlantasDetailEntity)

    // seleccionar de a 1 Planta por id
    @Query("SELECT * FROM planta_detail_table WHERE id=:id")
    fun getPlantasDetailById(id: Int): LiveData<PlantasDetailEntity>

}