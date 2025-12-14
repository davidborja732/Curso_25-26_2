package org.iesch.intermedio.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import org.iesch.intermedio.databinding.ItemAndroidVersionBinding
import org.iesch.intermedio.model.AndroidVersion

// Me creo el adaptador
class AndroidVersionAdapter(val versionesandroid: List<AndroidVersion>) : RecyclerView.Adapter<AndroidVersionViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AndroidVersionViewHolder {
        // inflar layout para cada item
        val binding= ItemAndroidVersionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AndroidVersionViewHolder( binding )
    }

    override fun onBindViewHolder(
        holder: AndroidVersionViewHolder,
        position: Int
    ) {
        holder.render(versionesandroid[position])
    }

    override fun getItemCount(): Int {
        return versionesandroid.size
    }


}