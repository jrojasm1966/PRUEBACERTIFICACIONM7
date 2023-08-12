package com.example.pruebacertificacion.Modelo.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/mauricioponce/TDApi/"

       lateinit var  retrofitInstance : Retrofit

       fun retrofitInstance(): PlantasApi{

           val retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
           return  retrofit.create(PlantasApi::class.java)
       }
    }
}