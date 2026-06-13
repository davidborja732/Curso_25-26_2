package org.iesch.intermedio.adapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.iesch.intermedio.databinding.ItemAndroidVersionBinding
import org.iesch.intermedio.model.AndroidVersion

class AndroidVersionViewHolder(val binding: ItemAndroidVersionBinding): RecyclerView.ViewHolder(binding.root) {
    fun render(androidversion: AndroidVersion){
        // asignar los datos a las vistas usando binding
        binding.tvNombreVersion.text=androidversion.nombre
        binding.tvDetalles.text="API ${androidversion.apilevel}-${androidversion.aniolanzamiento}"
        binding.tvCodigoNombre.text=androidversion.codigo
        itemView.setOnClickListener {
            Toast.makeText(itemView.context,androidversion.nombre+androidversion.codigo+androidversion.apilevel+androidversion.aniolanzamiento, Toast.LENGTH_SHORT).show()
        }
    }
}