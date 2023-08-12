package com.example.pruebacertificacion.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pruebacertificacion.ViewModel.PlantasViewModel
import com.example.pruebacertificacion.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var  mBinding : FragmentSecondBinding
    private val mViewModel : PlantasViewModel by activityViewModels()
    private var plantaId : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            plantaId = bundle.getInt("plantaId")
            Log.d("seleccion", plantaId.toString())
        }

        plantaId?.let { id ->
            mViewModel.getPlantasDetailByIdFromInternet(id)
        }

        mViewModel.getPlantasDetail().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion3", plantaId.toString())

            var id = it.id
            var nombre = it.nombre

        // cargamos los datos desde la seleccion
            Glide.with(mBinding.ivLogo).load(it.imagen).into(mBinding.ivLogo)
            mBinding.tvNombre.text = it.nombre
            mBinding.tvTipo.text = it.tipo
            mBinding.tvDescription.text = it.descripcion

            // ACCION DE ENVIAR CORREO Eléctronico
            mBinding.btMail.setOnClickListener {
                Log.d("Button", "Click")

                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type = "text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("luci@plantapp.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por Producto/Planta - Id: " + id + " Nombre: " + nombre
                )

                mintent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n" +
                            "Vi el Producto/Planta nombre " + nombre + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {

                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}