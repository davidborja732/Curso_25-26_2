package org.iesch.a03_menu_principal.apirazas
// Paquete donde se encuentra la actividad

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.apirazas.adapter.DogAdapter
import org.iesch.a03_menu_principal.apirazas.model.APIService
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityRazasApiBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// Importaciones necesarias para UI, corrutinas, Retrofit y binding

// 16 Como voy a usar el SearchView necesito decírselo al Activity
class RazasApiActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    // Declaramos la actividad que implementa el listener de búsqueda de SearchView

    private lateinit var binding: ActivityRazasApiBinding
    // Binding para acceder a las vistas del layout

    // 9
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()
    // Adaptador del RecyclerView y lista mutable de imágenes de perros

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge
        binding = ActivityRazasApiBinding.inflate(layoutInflater) // Infla el layout con ViewBinding
        setContentView(binding.root) // Establece el layout como contenido

        // Ajusta márgenes según barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 19
        binding.svDogs.setOnQueryTextListener(this) // Asocia el SearchView al listener implementado en la actividad
        // 1 - Creamos un método que inicie el RecyclerView
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // 10 Hemos de crear el adaptador
        adapter = DogAdapter(dogImages) // Inicializa el adaptador con la lista vacía
        binding.rvDogs.layoutManager = LinearLayoutManager(this) // Usa un LinearLayoutManager para mostrar los items en lista vertical
        binding.rvDogs.adapter = adapter // Asigna el adaptador al RecyclerView
    }

    // 5 - Creamos una instancia de Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/") // URL base de la API de perros
            .addConverterFactory(GsonConverterFactory.create()) // Conversor JSON → objetos Kotlin usando Gson
            .build()
    }

    // hasta aquí todo lo de retrofit
    // 6 - Implemento la función de buscar por razas
    private fun buscarPorRaza(raza: String) {
        CoroutineScope(Dispatchers.IO).launch {
            // Todo lo que se ejecute aquí se hace en un hilo secundario (IO)

            val call = getRetrofit().create<APIService>(APIService::class.java)
                .getPerrosPorRaza("$raza/images") // Llamada a la API con la raza indicada
            val puppies = call.body() // Obtiene el cuerpo de la respuesta

            // 11 - Estoy en un hilo secundario, para pintar la respuesta necesito volver al hilo principal
            runOnUiThread {
                // Como el if pintará un Toast o actualizará el RecyclerView, lo metemos en el hilo principal
                if (call.isSuccessful) {
                    // Mostraremos el RecyclerView
                    // 12 - Almacenamos en una variable las imágenes
                    val imagenes = puppies?.images ?: emptyList()
                    // 13 Primero borro todo lo que tengamos y añado los datos recibidos
                    dogImages.clear()
                    dogImages.addAll(imagenes)
                    // 14 - Avisamos al adaptador de que han habido cambios
                    adapter.notifyDataSetChanged()
                } else {
                    // 15 Mostraremos un error en un Toast
                    showError()
                }
                hideKeyBoard() // Ocultamos el teclado tras la búsqueda
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
        // Muestra un mensaje de error si la llamada falla
    }

    private fun hideKeyBoard() {
        // Ocultamos el teclado
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.main.windowToken, 0)
    }

    // 17 - Implementamos las dos funciones del SearchView.OnQueryTextListener
    override fun onQueryTextSubmit(query: String?): Boolean {
        // 18 Cuando pulsamos buscar se llamará a este método
        if (!query.isNullOrEmpty()) {
            buscarPorRaza(query.lowercase()) // Llama a la API con la raza en minúsculas
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Este método nos avisará cada vez que el texto cambie, y aquí no quiero hacer nada.
        return true
    }
}
