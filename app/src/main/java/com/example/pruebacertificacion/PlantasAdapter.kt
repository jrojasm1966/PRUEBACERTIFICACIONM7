package com.example.pruebacertificacion

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebacertificacion.Modelo.local.entities.PlantasDetailEntity
import com.example.pruebacertificacion.Modelo.local.entities.PlantasEntity
import com.example.pruebacertificacion.databinding.PlantasListBinding

class PlantasAdapter : RecyclerView.Adapter<PlantasAdapter.PlantaVH>(){

    // referencia para el adapter
    private var listPlantas = listOf<PlantasEntity>()
    private val selectedPlanta = MutableLiveData<PlantasEntity>()

    fun update (list:List<PlantasEntity>){
        listPlantas= list
        notifyDataSetChanged()
    }

    // función para seleccionar
    fun selectedPlanta():LiveData<PlantasEntity> = selectedPlanta

    inner class PlantaVH(private  val mbinding : PlantasListBinding):
            RecyclerView.ViewHolder(mbinding.root), View.OnClickListener{

                fun bind(planta: PlantasEntity){

                    Glide.with(mbinding.ivLogo).load(planta.imagen).centerCrop().into(mbinding.ivLogo)
                    mbinding.tvNombre.text= "Nombre: " + planta.nombre
                    mbinding.tvTipo.text= "Tipo: " + planta.tipo
                    mbinding.tvDescription.text= "Descripcion: " + planta.descripcion
                    itemView.setOnClickListener(this)

                }
        override fun onClick(v:View) {
            // referencia a la selección
            selectedPlanta.value= listPlantas[adapterPosition]
            Log.d("ONCLICK",adapterPosition.toString())
        }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantaVH {
     return PlantaVH(PlantasListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listPlantas.size

    override fun onBindViewHolder(holder: PlantaVH, position: Int) {
       val planta= listPlantas[position]
        holder.bind(planta)
    }
}