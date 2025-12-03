package org.iesch.a03_menu_principal.apirazas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.practica_6.david.R

// 2 - Nuestro adaptador recibirá una lista de Strings, le tendremos que pasar el ViewHolder
class DogAdapter(val images: List<String>) : RecyclerView.Adapter<DogViewHolder>() {
    // Definimos una clase llamada DogAdapter que recibe una lista de Strings (urls o paths de imágenes).
    // Hereda de RecyclerView.Adapter y usará un ViewHolder personalizado llamado DogViewHolder.

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogViewHolder {
        // Método que se ejecuta cuando el RecyclerView necesita crear un nuevo ViewHolder.
        // Aquí inflamos el layout que representa cada elemento de la lista.

        val layoutInflater = LayoutInflater.from(parent.context)
        // Obtenemos un objeto LayoutInflater desde el contexto del padre (RecyclerView).

        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
        // Inflamos el layout "item_dog" y lo pasamos al constructor de DogViewHolder.
        // El "false" indica que no se añade directamente al parent, RecyclerView lo hará.
    }

    override fun onBindViewHolder(
        holder: DogViewHolder,
        position: Int
    ) {
        // Método que se ejecuta para "vincular" datos a un ViewHolder ya creado.
        // Aquí asignamos la información correspondiente a la posición actual.

        val item = images[position]
        // Obtenemos el String (por ejemplo, url de imagen) de la lista en la posición indicada.

        holder.render(item)
        // Llamamos al método render del DogViewHolder para que muestre la imagen en la UI.
    }

    override fun getItemCount(): Int {
        // Método que devuelve el número total de elementos que tendrá el RecyclerView.
        // Es decir, el tamaño de la lista de imágenes.

        return images.size
        // Retorna la cantidad de elementos de la lista "images".
    }
}
