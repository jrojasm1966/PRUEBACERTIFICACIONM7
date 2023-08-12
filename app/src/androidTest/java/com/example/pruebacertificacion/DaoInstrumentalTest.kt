package com.example.pruebacertificacion

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pruebacertificacion.Modelo.local.PlantasDao
import com.example.pruebacertificacion.Modelo.local.database.PlantasDataBase
import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.local.entities.PlantasEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoInstrumentalTest {

private lateinit var plantasDao: PlantasDao
private lateinit var db: PlantasDataBase

@Before
fun setUp(){
    val context= ApplicationProvider.getApplicationContext<android.content.Context>()
    db= Room.inMemoryDatabaseBuilder(context,PlantasDataBase::class.java).build()
    plantasDao= db.getPlantasDao()
}

@After
fun shutDown(){
    db.close()

}


@Test
fun insertCoursesList() = runBlocking {

    val plantasEntities= listOf(
        PlantasEntity (1,"prueba","test1","url","March"),
        PlantasEntity (2,"prueba2","test2","url","March")

    )

    plantasDao.insertAllPlantas(plantasEntities)

    val plantasLiveData = plantasDao.getAllPlantas()
    val plantasList : List<PlantasEntity> = plantasLiveData.value?: emptyList()

    // verificamos el listado
    // verificamos el listado si no es vacio
    assertThat(plantasList, not(emptyList()))
    assertThat(plantasList.size,equalTo(2))

}

@Test
fun insertDetailPlanta()= runBlocking {

    val plantasDetail = PlantasDetailEntity(

        1,
        "Rosa",
        "Flor",
        "url",
        "Planta"
    )

    plantasDao.insertPlantasDetail(plantasDetail)
     val plantasLiveData= plantasDao.getPlantasDetailById(1)
     val plantaValue= plantasLiveData.value

    assertThat(plantaValue?.id, equalTo(1))
    assertThat(plantaValue?.nombre, equalTo("Planta"))
}

}