package com.example.pruebacertificacion.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pruebacertificacion.Modelo.PlantasRepository
import com.example.pruebacertificacion.Modelo.local.database.PlantasDataBase
import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.local.entities.PlantasEntity
import kotlinx.coroutines.launch

class PlantasViewModel(application: Application) : AndroidViewModel(application){

    // conexión repository
    private val repository : PlantasRepository

    // entidades
    private val plantasDetailLiveData = MutableLiveData<PlantasDetailEntity>()

    // para seleccionar
    private var idSelected : String="-1"

    init{
        // tiene la instancia de la bd el dao y lo entregamos el repository
        val bd= PlantasDataBase.getDataBase(application)
        val PlantasDao= bd.getPlantasDao()

        repository = PlantasRepository(PlantasDao)

       // llamo al método del repository
       viewModelScope.launch {
           repository.fetchCourse()
       }
    }

    // listado de los elementos
    fun getPlantasList(): LiveData<List<PlantasEntity>> = repository.PlantasListLiveData

    // para obtener una planta por id desde lo que se selecciono
    fun getPlantasDetail(): LiveData<PlantasDetailEntity> = plantasDetailLiveData

    // desde el segundo fragmento le paso la seleccion
    fun getPlantasDetailByIdFromInternet(id: Int)= viewModelScope.launch {
        val courseDetail = repository.fetchPlantasDetail(id)
        courseDetail?.let {
            plantasDetailLiveData.postValue(it)
        }
    }
}