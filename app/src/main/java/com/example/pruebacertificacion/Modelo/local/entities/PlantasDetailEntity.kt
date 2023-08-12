package com.example.pruebacertificacion.Modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="planta_detail_table")
data class PlantasDetailEntity(

    @PrimaryKey
    val id : Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String
)