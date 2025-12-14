package org.iesch.a03_menu_principal.apirazas.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.iesch.a03_menu_principal.databinding.ItemDogBinding

// 3 - Esta clase recibirá la vista que vamos a pintar
class DogViewHolder( view : View ) : RecyclerView.ViewHolder(view ) {

    // 4 - Creamos el metodo que recibira una imagen por cada celda que tenemos que pintar

    private val binding = ItemDogBinding.bind( view )
    fun render( imagen : String ){
        // 8 - A través de la librería Picasso mostraremos la imagen a partir de la url
        Picasso.get().load( imagen ).into( binding.ivDog )
    }
}