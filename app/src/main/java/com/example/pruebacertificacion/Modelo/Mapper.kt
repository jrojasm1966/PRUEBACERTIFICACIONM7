package com.example.pruebacertificacion.Modelo

import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.local.entities.PlantasEntity
import com.example.pruebacertificacion.Modelo.remote.frominternet.PlantasDetail
import com.example.pruebacertificacion.Modelo.remote.frominternet.Plantas


fun fromInternetPlantasEntity(plantasList: List<Plantas>) :List<PlantasEntity>{

    return plantasList.map {
        PlantasEntity(
            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion
            )
        }
}

fun fromInternetPlantasDetailEntity( planta: PlantasDetail) :PlantasDetailEntity{

    return PlantasDetailEntity(
        id = planta.id,
        nombre = planta.nombre,
        tipo = planta.tipo,
        imagen = planta.imagen,
        descripcion = planta.descripcion
        )
    }







