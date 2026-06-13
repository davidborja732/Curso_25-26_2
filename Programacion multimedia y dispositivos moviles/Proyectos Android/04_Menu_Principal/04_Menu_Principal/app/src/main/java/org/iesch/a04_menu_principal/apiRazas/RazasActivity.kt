package org.iesch.a04_menu_principal.apiRazas

import android.os.Bundle
import android.view.inputmethod.InputMethod
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
import org.iesch.a04_menu_principal.R
import org.iesch.a04_menu_principal.apiRazas.adapter.DogAdapter
import org.iesch.a04_menu_principal.apiRazas.model.APIService
import org.iesch.a04_menu_principal.databinding.ActivityRazasBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RazasActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    // Declaramos la variable lista y el adpater
    private lateinit var adapter: DogAdapter
    private val dogImages=mutableListOf<String>()
    private lateinit var binding: ActivityRazasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityRazasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title="Perretes"
        binding.svdogs.setOnQueryTextListener(this)
        // Creamos un metodo que incia el Recyclerview
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // Hemos de crear el adaptador
        adapter= DogAdapter(dogImages)
        binding.rvdogs.layoutManager = LinearLayoutManager(this)
        binding.rvdogs.adapter=adapter
    }

    // Creamos la instancia de retrofit
    private fun getRetroflit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // hasta aqui todo lo de retloflit
    // Implemento la funcion de buscar por razas
    private fun buscarporraza(raza: String){
        CoroutineScope(Dispatchers.IO).launch{
            // Todo lo que se ejecute aqui se ejecuta en un hilo segundario
            val call=getRetroflit().create<APIService>(APIService::class.java).getPerrosPorRaza("$raza/images")
            val puppies = call.body()
            // Estoy en un hilo segundario y para pintar la respuesta necesito volver al hilo principal
            // lo hare mediante  runOnUiThread
            runOnUiThread {
                // como el if pintara un toast o el recycler lo metemos en el hilo principal
                if (call.isSuccessful){
                    // mostraremos el recycledview
                    // Almacenamos en una variable las imagenes
                    val imagenes= puppies?.images ?: emptyList()
                    //Borro lo que tengamos y añado los datos recibidos
                    dogImages.clear()
                    dogImages.addAll(imagenes)
                    // Avisamos al adaptador de que han habido cambios
                    adapter.notifyDataSetChanged()
                }else{
                    // Mostraremos un error en un toast
                    showError()
                }
                hidekeyboard()
            }
        }
    }
    private fun hidekeyboard(){
        val imn=getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.main.windowToken,0)
    }
    private fun showError() {
        Toast.makeText(this,"No se puedo encontrar la raza", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // Cuando pulsamos buscar se llamara a este metodo
        if (!query.isNullOrEmpty()){
            buscarporraza(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Este metodo nos avisara cada vez que el texto cambie
        return true
    }
}