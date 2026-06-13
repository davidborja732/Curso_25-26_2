package org.iesch.a03_menu_principal.settings

import android.content.Context
import android.os.Bundle
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
import org.iesch.a03_menu_principal.settings.model.SettingsData
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivitySettingsBinding


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LEVEL = "volume_level"

        //3b
        const val KEY_DARKMODE = "darkmode_enabled"
        const val KEY_BLUETOOTH = "bluetooth_enabled"
        const val KEY_VIBRATION = "vibration_enabled"
    }

    private lateinit var binding: ActivitySettingsBinding

    // 8
    private var firstTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 6 Llamo a la funcion para obtener los datos guardados
        // Vamos a consumir ese Flow
        CoroutineScope(Dispatchers.IO).launch {
            getSettigs().filter { firstTime }.collect { datosAlmacenados ->
                // 7 Actualizar la UI en el hilo principal. NO se puede tocar la interfaz desde un hilo secundario
                CoroutineScope(Dispatchers.Main).launch {
                    binding.swDarkmode.isChecked = datosAlmacenados?.darkMode ?: false
                    binding.swBluetooth.isChecked = datosAlmacenados?.bluetoothEnabled ?: false
                    binding.swVibracion.isChecked = datosAlmacenados?.vibrationEnabled ?: true
                    binding.rsVolumen.setValues(datosAlmacenados?.volumen?.toFloat())
                    firstTime = !firstTime
                }

            }
        }

        initUI()
    }

    private fun initUI() {
        binding.rsVolumen.addOnChangeListener { _, value, _ ->
            // 1 - Llamamos a guardar volumen desde una corrutina
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
            // Con esto almacenamos el valor
        }
        // 3 Creamos el resto de funciones y variables de KEY
        binding.swDarkmode.setOnCheckedChangeListener { // El primer parámetro es el boton
                _, value ->
            // 10
            if (value) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARKMODE, value)
            }
        }
        binding.swBluetooth.setOnCheckedChangeListener { // El primer parámetro es el boton
                _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }
        binding.swVibracion.setOnCheckedChangeListener { // El primer parámetro es el boton
                _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION, value)
            }
        }
    }


    private suspend fun saveVolume(value: Int) {
        // Aquí irá el código para guardar datos en el DataStore
        // No puede ser llamado desde fuera de una corrutina
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LEVEL)] = value
        }
    }

    // 2 Funcion para guardar los checks, le paso el key y el valor
    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    // 4 Necesito una única funcion que me va a devolver todos los valores
    private fun getSettigs(): Flow<SettingsData?> {
        return dataStore.data.map { preferences ->

            SettingsData(
                preferences[intPreferencesKey(VOLUME_LEVEL)] ?: 50,
                preferences[booleanPreferencesKey(KEY_DARKMODE)] ?: false,
                preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false,
                preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: false
            )

            // preferences[booleanPreferencesKey(KEY_DARKMODE)]
            // datastore solo permite devolver un unico valor.
            // Entonces lo que haremos será crear un objeto que envuelva todos los valores que necesitamos
        }
    }

    // 9 - Me creo las funciones para cambiar el modo a oscuro o claro
    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}