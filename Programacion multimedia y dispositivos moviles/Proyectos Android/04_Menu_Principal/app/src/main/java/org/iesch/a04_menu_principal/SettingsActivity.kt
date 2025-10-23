package org.iesch.a04_menu_principal

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import org.iesch.a04_menu_principal.databinding.ActivitySettingsBinding
import kotlin.reflect.KTypeProjection

// Me creo una funcion de extension
// Nos permiten a traves de un componente crear metodos o propiedades adiconales sin necesidad de heredar de la clase original
// Esta funcion hereda del context
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
// Este delegado nos permite crear una unica instancia de la base de datos
// name es el nombre de la base de datos

class SettingsActivity : AppCompatActivity() {
    companion object{
        const val VOLUME_LEVEL="volume_level"
    }
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun initUI(){
        binding.slvolumen.addOnChangeListener {_,value,_ ->
            Log.i("David","Guaradando valor de volumen")
        }
    }
    private suspend fun guardarVolumen(value: Int){
        // Aqui ira el codigo para guardar datos en el datastore
        // No puede ser llamado desde fuera de una corrutina
        dataStore.edit {
            it[intPreferencesKey("VOLUME_LEVEL")] = value
        }
    }
}