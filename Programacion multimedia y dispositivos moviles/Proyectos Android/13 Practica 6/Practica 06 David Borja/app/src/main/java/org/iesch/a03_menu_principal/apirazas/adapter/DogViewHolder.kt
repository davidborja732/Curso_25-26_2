package org.iesch.a03_menu_principal.apirazas.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.practica_6.david.databinding.ItemDogBinding

// 3 - Esta clase recibirá la vista que vamos a pintar
class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Definimos la clase DogViewHolder que hereda de RecyclerView.ViewHolder.
    // Recibe una vista (view) que representa cada celda del RecyclerView.

    // 4 - Creamos el método que recibirá una imagen por cada celda que tenemos que pintar

    private val binding = ItemDogBinding.bind(view)
    // Usamos ViewBinding para acceder directamente a los elementos del layout "item_dog".
    // El método bind() conecta la vista inflada con las referencias del layout.

    fun render(imagen: String) {
        // Método que recibe un String (normalmente una URL de imagen) y se encarga de mostrarla en la celda.

        // 8 - A través de la librería Picasso mostraremos la imagen a partir de la url
        Picasso.get().load(imagen).into(binding.ivDog)
        // Usamos la librería Picasso para cargar la imagen desde la URL "imagen".
        // La imagen se coloca en el ImageView "ivDog" definido en el layout item_dog.
    }
}
