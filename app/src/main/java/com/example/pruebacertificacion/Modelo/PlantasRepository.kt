package com.example.pruebacertificacion.Modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pruebacertificacion.Modelo.local.PlantasDao
import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.remote.RetrofitClient

class PlantasRepository(private val plantasDao: PlantasDao) {

    // retrofit Cliente
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
     val PlantasListLiveData = plantasDao.getAllPlantas()

    // un elemento
    val plantaDetailLiveData= MutableLiveData<PlantasDetailEntity>()

    suspend fun fetchCourse(){
        val service = kotlin.runCatching { networkService.fetchPlantasList() }

        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {

                    Log.d("Plantas",it.toString())

                  plantasDao.insertAllPlantas(fromInternetPlantasEntity(it))
                }
                else-> Log.d("Repo","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }

        suspend fun fetchPlantasDetail(id: Int): PlantasDetailEntity?{
        val service = kotlin.runCatching { networkService.fetchPlantasDetail(id) }
        return service.getOrNull()?.body()?.let { PlantasDetail ->
            // guardar los datos que viene del mapper y luego se pasan a dao directo
            val plantasDetailEntity = fromInternetPlantasDetailEntity(PlantasDetail)
            //inserto los detalles de los curso DEL REPOSITORIO
            plantasDao.insertPlantasDetail(plantasDetailEntity)
            plantasDetailEntity
        }
    }











}