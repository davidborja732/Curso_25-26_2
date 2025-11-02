package org.iesch.a04_menu_principal.apiRazas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.iesch.a04_menu_principal.R

// Nuestro adaptador recibira una lista de Strings, le tendremos que pasar el Viewholder
class DogAdapter(val images: List<String>) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogViewHolder {
        // Aqui tendremos que inflar el layout del item qu enos hemos creado
        val layoutInflater= LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(
        holder: DogViewHolder,
        position: Int
    ) {
        // Crearemos el item que sera la imagen y la posicion que tendra
        // Llamamos al holder y le pasamos el item
        val item=images[position]
        holder.render(item)
    }

    override fun getItemCount(): Int { // Pasamos y devolvera el tamaño de la lista que tengamos
        return images.size
    }

}