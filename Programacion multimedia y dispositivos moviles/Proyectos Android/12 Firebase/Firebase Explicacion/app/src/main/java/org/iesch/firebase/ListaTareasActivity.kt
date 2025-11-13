package org.iesch.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import org.iesch.firebase.databinding.ActivityListaTareasBinding
import org.iesch.firebase.model.Tarea
import org.iesch.firebase.recycler.TareaAdapter
import kotlin.concurrent.thread

class ListaTareasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaTareasBinding
    // Creamos la instancia para la base de datos
    private val  dbTareas= FirebaseFirestore.getInstance()
    // Creeamos la lista de tareas mutable
    private val listaTareas=mutableListOf<Tarea>()
    // Creamos el adaptador
    private lateinit var tareaAdapter: TareaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityListaTareasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Configuramos el RecyclerView
        configurarRecyclerView()
        // Cargamos las tareas desde firestore
        cargarTareas()
        binding.agregarboton.setOnClickListener {
            agregarTarea()
        }
    }

    private fun agregarTarea() {

        val titulo=binding.etTitulo.text.toString().trim()
        val descripcion=binding.etDescripcion.text.toString().trim()
        if (titulo.isEmpty() || descripcion.isEmpty()){
            Toast.makeText(this,"Error al cargar tareas", Toast.LENGTH_SHORT).show()
        }else{
            val nuevaTareaRef=dbTareas.collection("tareas").document()
            val nuevaTarea= Tarea(
                id = nuevaTareaRef.id,
                titulo=titulo,
                descripcion=descripcion,
                completada = false
            )
            nuevaTareaRef.set(nuevaTarea)
                .addOnCompleteListener {
                    Toast.makeText(this,"Tarea añadida correctamente", Toast.LENGTH_SHORT).show()
                    // Limpiamos los campos titulo y descripcion
                    binding.etTitulo.text.clear()
                    binding.etDescripcion.text.clear()
                    cargarTareas()
                }.addOnFailureListener {
                    Toast.makeText(this,"Error al añadir tareas", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun cargarTareas() {
        mostrarLoading(true)
        dbTareas.collection("tareas")
            .addSnapshotListener { snapshot,error ->
                if (error!=null){
                    Toast.makeText(this,"Error al cargar tareas", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                listaTareas.clear()
                snapshot?.documents?.forEach { documentSnapshot ->
                    val tarea= Tarea(
                        id = documentSnapshot.id,
                        titulo=documentSnapshot.getString("titulo")?:"",
                        descripcion=documentSnapshot.getString("descripcion")?:"",
                        completada = documentSnapshot.getBoolean("completada")?:false
                    )
                    listaTareas.add(tarea)
                }
                tareaAdapter.actualizarlista(listaTareas)
                mostrarLoading(false)
            }
            /*.get()
            .addOnCompleteListener { result ->
                listaTareas.clear()
                for (tareas in result.result){
                    val tarea=tareas.toObject(Tarea::class.java)
                    listaTareas.add(tarea)
                }
                tareaAdapter.actualizarlista(listaTareas)
            }.addOnFailureListener {
                Toast.makeText(this,"Error al cargar tareas", Toast.LENGTH_SHORT)
            }*/
    }

    private fun configurarRecyclerView() {
        tareaAdapter=TareaAdapter(listaTareas, onBorrar = {tarea -> borrarTarea(tarea)},
            onToggleCompletada = {tarea -> marcarCompletada(tarea)})
        // Asignamos el adaptador a nuestro RecyclerView
        binding.RVTareas.adapter=tareaAdapter
        binding.RVTareas.layoutManager= androidx.recyclerview.widget.LinearLayoutManager(this)

    }
    private fun borrarTarea(tarea: Tarea){
        dbTareas.collection("tareas")
            .document(tarea.id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this,"Tarea eliminada correctamente", Toast.LENGTH_SHORT).show()
            }
    }
    private fun marcarCompletada(tarea: Tarea){
        dbTareas.collection("tareas")
            .document(tarea.id)
            .update("completada",tarea.completada)
            .addOnCompleteListener {
                Toast.makeText(this,"Estado tarea almaacenado correctamente", Toast.LENGTH_SHORT).show()
            }
    }
    private fun mostrarLoading(mostrar: Boolean){
        binding.barrita.visibility=if (mostrar){
            android.view.View.VISIBLE
        }else{
            android.view.View.INVISIBLE
        }
    }
}