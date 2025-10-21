package org.iesch.explicacion_fragmentos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.iesch.explicacion_fragmentos.R

const val NAME_BUNDLE = "name_bundle"
const val ADDRESS = "adress"

class Primer_flagment : Fragment() {
    // TODO: Rename and change types of parameters
    private var nombre: String? = null
    private var direccion: String? = null
    // Este metodo se llama cuando la vista ha sido cargada
    //Cuando llamamos a la instancia llamamos a onCreate y aqui le preguntamos su hay algun elemento
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString(NAME_BUNDLE)
            direccion = it.getString(ADDRESS)
            Log.i("nombre", nombre.orEmpty())
            Log.i("direccion", direccion.orEmpty())

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_primer_flagment, container, false)
    }
    // Los fragments se suelen llamar a atraves de un metodo publico llamado new instance
    // lo que tenemos aqui son los parametros que quiero que reciba
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Primer_flagment().apply {
                // Cogo el atributo elementos de este fragment y le paso un Bundle
                arguments = Bundle().apply {
                    putString(NAME_BUNDLE, param1)
                    putString(ADDRESS, param2)
                }
            }
    }
}