package org.iesch.a03_menu_principal.apirazas

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
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.apirazas.adapter.DogAdapter
import org.iesch.a03_menu_principal.apirazas.model.APIService
import org.iesch.a03_menu_principal.databinding.ActivityRazasApiBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// 16 Como voy a usar el SearchView necesito decírselo al Activity
class RazasApiActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityRazasApiBinding

    // 9
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRazasApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 19
        binding.svDogs.setOnQueryTextListener( this )
        //  1 - Creamos un metodo que inicie el RecyclerView
        initRecyclerView()

    }

    private fun initRecyclerView() {
        // 10 Hemos de crear el adaptador
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    // 5 - Creamos una instancia de Retrofit
    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // hasta aqui to do lo de retrofit
    // 6 - Implemento la funcion de buscar por razas
    private fun buscarPorRaza( raza : String ){
        CoroutineScope(Dispatchers.IO).launch {
            // To do lo que se ejecute aquí se está ejecutando en un hilo secundario
            val call = getRetrofit().create<APIService>(APIService::class.java).getPerrosPorRaza("$raza/images")
            val puppies = call.body()
            // 11 - Estoy en un hilo secundario, ya para pintar la respuesta necesito volver al hilo principal
            // Lo haré mediante runOnUiThread

            runOnUiThread {
                // Como el if pintará un Toast o el recycler lo metemos en el hilo principal
                if ( call.isSuccessful ){
                    // Mostraremos el RecyclerView
                    // 12 - Almacenamos en una variable las imagenes.
                    val imagenes = puppies?.images ?: emptyList()
                    // 13 Primero borro to do lo que tengamos y añado los datos recibidos
                    dogImages.clear()
                    dogImages.addAll( imagenes )
                    // 14 - Avisamos al adaptador de que han habido cambios
                    adapter.notifyDataSetChanged()
                } else {
                    // 15 Mostraremos un error en un Toast
                    showError()
                }
                hideKeyBoard()
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

    private fun hideKeyBoard(){
        // Ocultamos el teclado
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.main.windowToken, 0)
    }

    // 17 - Implementamos las dos funciones y las completamos
    override fun onQueryTextSubmit(query: String?): Boolean {
        // 18 Cuando pulsamos buscar se llamará a este mét odo
        if ( !query.isNullOrEmpty() ){
            buscarPorRaza( query.lowercase() )
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Este metodo nos avisará cada vez que el texto cambie, y aquí no quiero hacer nada.
        return true
    }



}