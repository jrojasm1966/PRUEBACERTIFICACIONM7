package com.example.pruebacertificacion.Modelo.remote

import com.example.pruebacertificacion.Modelo.remote.frominternet.PlantasDetail
import com.example.pruebacertificacion.Modelo.remote.frominternet.Plantas
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantasApi {

    @GET("plantas")
    suspend fun fetchPlantasList(): Response<List<Plantas>>

    @GET("plantas/{id}")
    suspend fun fetchPlantasDetail(@Path("id") id:Int) : Response<PlantasDetail>

}