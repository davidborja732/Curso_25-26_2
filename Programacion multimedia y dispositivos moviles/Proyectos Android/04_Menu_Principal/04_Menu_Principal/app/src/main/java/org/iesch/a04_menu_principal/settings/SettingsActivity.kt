package org.iesch.a04_menu_principal.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.iesch.a04_menu_principal.R
import org.iesch.a04_menu_principal.databinding.ActivitySettingsBinding

// Me creo una funcion de extension
// Nos permiten a traves de un componente crear metodos o propiedades adiconales sin necesidad de heredar de la clase original
// Esta funcion hereda del context
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
// Este delegado nos permite crear una unica instancia de la base de datos
// name es el nombre de la base de datos

class SettingsActivity : AppCompatActivity() {
    companion object{
        const val VOLUME_LEVEL="volume_level"
        const val DARK_MODE_KEY="darkmode_enabled"
        const val BLUETOOTH_KEY="bluetooth_enabled"
        const val VIBRATION_KEY="vibration_enabled"
    }
    private lateinit var binding: ActivitySettingsBinding
    private var primeravez: Boolean=true
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
        CoroutineScope(Dispatchers.IO).launch {
            obtenerAjustes().filter { primeravez }.collect { datos ->
                //actualizar la UI en el hilo principal no se puede tocar la interfaz desde un hilo segundario
                CoroutineScope(Dispatchers.Main).launch{
                    binding.swDarkMode.isChecked=datos?.darkmode?:false
                    binding.swBluetooth.isChecked=datos?.bluetooth?:false
                    binding.slvolumen.setValues(datos?.volumen?.toFloat()?:50f)
                    binding.swVibracion.isChecked=datos?.vibracion?:false
                }
            }
        }
        initUI()

    }
    private fun initUI(){
        binding.slvolumen.addOnChangeListener {_,value,_ ->
            Log.i("David","Guardando valor de volumen "+value)
            CoroutineScope(Dispatchers.IO).launch {
                // almacenamos el valor
                guardarVolumen(value.toInt())
            }
        }
        // El primer parametro es el boton
        binding.swDarkMode.setOnCheckedChangeListener {_,value ->
            if (value){
                activarmodooscuro()
            }else{
                desactivarmodooescuro()
            }
            CoroutineScope(Dispatchers.IO).launch {
                guardarOpciones(DARK_MODE_KEY,value)
            }
        }
        binding.swBluetooth.setOnCheckedChangeListener {_,value->
            CoroutineScope(Dispatchers.IO).launch {
                guardarOpciones(BLUETOOTH_KEY,value)
            }
        }
        binding.swVibracion.setOnCheckedChangeListener {_,value->
            CoroutineScope(Dispatchers.IO).launch {
                guardarOpciones(VIBRATION_KEY,value)
            }
        }
    }
    private suspend fun guardarVolumen(value: Int){
        // Aqui ira el codigo para guardar datos en el datastore
        // No puede ser llamado desde fuera de una corrutina
        dataStore.edit {
            it[intPreferencesKey("VOLUME_LEVEL")] = value
        }
    }
    // Funcion para guardar los checks
    private suspend fun guardarOpciones(key: String, value: Boolean){
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }
    // Necesito una unica funcion que me va a devolver todos los valores
    private fun obtenerAjustes(): Flow<SettingsData?> {
        // datastore solo permite devolver un unico valor crearemos un objeto que encapsule todos los valores que necesitamos
        return dataStore.data.map { preferences ->
            SettingsData(
                preferences[intPreferencesKey(VOLUME_LEVEL)]?:50,
                preferences[booleanPreferencesKey(DARK_MODE_KEY)]?:false,
                preferences[booleanPreferencesKey(BLUETOOTH_KEY)]?:false,
                preferences[booleanPreferencesKey(VIBRATION_KEY)]?:false
            )
        }
    }
    // Me creo ls funciones para cambiar el modo a oscuro
    private fun activarmodooscuro(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun desactivarmodooescuro(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}
