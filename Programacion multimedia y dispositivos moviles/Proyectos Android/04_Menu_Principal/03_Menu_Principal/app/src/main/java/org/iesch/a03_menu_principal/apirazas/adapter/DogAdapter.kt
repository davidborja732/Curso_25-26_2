package org.iesch.a03_menu_principal.apirazas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.iesch.a03_menu_principal.R

// 2 - Nuestro adaptador recibirá una lista de Strings, le tendremos que pasar el ViewHolder
class DogAdapter( val images : List<String> ) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogViewHolder {
        // 7 - Aquí tendremos que inflar el layout del item que nos hemos creado para cada respuesta
        val layoutInflater = LayoutInflater.from( parent.context )
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(
        holder: DogViewHolder,
        position: Int
    ) {
        // 6 - Crearemos el item que será la imagen y la posicion que tenga
        // llamamos al holder y le pasamos el item
        val item = images[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        // 5 - Devoilverá el tamaño de lista que tengamos
        return images.size
    }
}