package org.iesch.a04_menu_principal.apiRazas.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.iesch.a04_menu_principal.databinding.ItemDogBinding

// La clase recibira la vista que vamos a pintar
class DogViewHolder( view: View) : RecyclerView.ViewHolder(view){
    // Creamos el metodo que recibira una imagen por cada celda que tendremos que pintar
    private  val binding= ItemDogBinding.bind(view)
    fun render(imagen: String){
        // A traves de la libreria picasso mostraremos la imagen a partir de la url
        Picasso.get().load(imagen).into(binding.ivDog)
    }
}