package org.iesch.firebase.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.google.type.Color
import org.iesch.firebase.databinding.ItemTareaBinding
import org.iesch.firebase.model.Tarea
import org.iesch.firebase.recycler.TareaAdapter.*
import androidx.core.graphics.toColorInt
import kotlin.also

// Creamos el adaptador
class TareaAdapter(private var listaTareas: MutableList<Tarea>,private val onBorrar:((Tarea) -> Unit)?=null,private val onToggleCompletada:((Tarea) -> Unit)?=null): RecyclerView.Adapter<TareaViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TareaViewHolder {
        // Inflo el layout del item
        val binding= ItemTareaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        // Devuelvo el viewHolder
        return TareaViewHolder (binding)
    }

    override fun onBindViewHolder(
        holder: TareaViewHolder,
        position: Int
    ) {
        // LLamo al bind del viewholder
        holder.bind(listaTareas[position],onBorrar,onToggleCompletada)
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun actualizarlista(nuevalista: MutableList<Tarea>){
        listaTareas=nuevalista
        notifyDataSetChanged()
    }

    // Creo ViewHolder
    class TareaViewHolder(private val binding: ItemTareaBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(tarea: Tarea,onBorrar: ((Tarea) -> Unit)?=null,onToggleCompletada: ((Tarea) -> Unit)?=null){
            binding.tvTitulo.text=tarea.titulo
            binding.tvDescripcion.text=tarea.descripcion
            // Pongo a true o false
            binding.completada.setOnCheckedChangeListener(null)
            binding.completada.isChecked=tarea.completada
            aplicarEstiloCompletado(tarea.completada)
            binding.imgBtnBorrar.setOnClickListener {
                onBorrar?.invoke(tarea)
            }
            binding.completada.setOnCheckedChangeListener { _,isChecked ->
                //Actualizamos el valor del objeto
                aplicarEstiloCompletado(isChecked)
                onToggleCompletada?.invoke(tarea)
            }
        }

        private fun aplicarEstiloCompletado(completada: Boolean) {
            if (completada) {
                binding.tarjetita.setBackgroundColor("green".toColorInt())
            }else{
                binding.tarjetita.setBackgroundColor("purple".toColorInt())
            }
        }


    }
}
