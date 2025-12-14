package org.iesch.a03_menu_principal.ajustes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.datos.dataStore
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityAjustesBinding

class AjustesActivity : AppCompatActivity() { // Declaración de la actividad AjustesActivity que hereda de AppCompatActivity

    private lateinit var binding: ActivityAjustesBinding // Variable para acceder al layout mediante ViewBinding
    private var email: String? = null // Variable para guardar el email del usuario (puede ser nulo)
    private var provider: String? = null // Variable para guardar el proveedor de autenticación (puede ser nulo)
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance() // Instancia de la base de datos Firestore

    override fun onCreate(savedInstanceState: Bundle?) { // Método que se ejecuta al crear la actividad
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase padre
        enableEdgeToEdge() // Activa el diseño "edge-to-edge" para que la UI ocupe toda la pantalla
        binding = ActivityAjustesBinding.inflate(layoutInflater) // Infla el layout usando ViewBinding
        setContentView(binding.root) // Establece el layout como contenido de la actividad

        email = intent.getStringExtra("usuario") // Recupera el email del usuario desde el Intent
        provider = intent.getStringExtra("provider") // Recupera el proveedor de autenticación desde el Intent

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets -> // Listener para ajustar márgenes según barras del sistema
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene los márgenes de las barras de sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Aplica padding a la vista principal
            insets // Devuelve los insets para continuar el flujo
        }

        binding.titulo.text = email ?: "Usuario desconocido" // Muestra el email en el título o "Usuario desconocido" si es nulo

        binding.interruptorModoOscuro.setOnCheckedChangeListener { _, isChecked -> // Listener para el switch de modo oscuro
            guardarDato("${email}-modo_oscuro", isChecked.toString()) // Guarda el estado del modo oscuro en DataStore
            binding.datoModoOscuro.text =
                "Modo Oscuro: ${if (isChecked) "Activation" else "Deactivations"}" // Actualiza el texto del estado (mezcla inglés/español)
            val modo =
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO // Determina el modo según el switch
            AppCompatDelegate.setDefaultNightMode(modo) // Aplica el modo oscuro o claro a la app
        }

        binding.interruptorNotificaciones.setOnCheckedChangeListener { _, isChecked -> // Listener para el switch de notificaciones
            if (isChecked) {
                binding.datoNotificaciones.text = "Notification: Activadas" // Actualiza texto a "Activadas"
                guardarDato("${email}-notificaciones", "true") // Guarda preferencia de notificaciones activadas
                FirebaseMessaging.getInstance().isAutoInitEnabled = true // Activa inicialización automática de FCM
            } else {
                binding.datoNotificaciones.text = "Notification: Desactivadas" // Actualiza texto a "Desactivadas"
                guardarDato("${email}-notificaciones", "false") // Guarda preferencia de notificaciones desactivadas
                FirebaseMessaging.getInstance().isAutoInitEnabled = false // Desactiva inicialización automática de FCM
            }
        }

        binding.botonVolver.setOnClickListener { // Listener para el botón "volver"
            finish() // Cierra la actividad y regresa a la anterior
        }

        cargarDatosGuardados() // Llama a la función para cargar preferencias guardadas
        cargarNombreDesdeFirestore() // Llama a la función para cargar el nombre del usuario desde Firestore
    }

    private fun guardarDato(clave: String, valor: String) { // Función para guardar un dato en DataStore
        val key = stringPreferencesKey(clave) // Crea una clave de tipo String para DataStore
        lifecycleScope.launch { // Inicia una corrutina ligada al ciclo de vida
            applicationContext.dataStore.edit { prefs -> // Edita las preferencias de DataStore
                prefs[key] = valor // Guarda el valor en la clave correspondiente
            }
        }
    }

    private fun cargarDatosGuardados() { // Función para cargar datos guardados en DataStore
        lifecycleScope.launch { // Inicia corrutina
            val modoOscuro = leerDato("${email}-modo_oscuro") // Lee el estado del modo oscuro
            val notificaciones = leerDato("${email}-notificaciones") // Lee el estado de las notificaciones
            binding.datoMetodo.text = "Proveedor: ${provider?.ifEmpty { "No establecido" }}" // Muestra el proveedor o "No establecido"
            binding.datoModoOscuro.text =
                "Modo Oscuro: ${if (modoOscuro == "true") "Activado" else "Desactivado"}" // Actualiza texto del modo oscuro
            binding.datoNotificaciones.text =
                "Notificaciones: ${if (notificaciones == "true") "Activadas" else "Desactivadas"}" // Actualiza texto de notificaciones

            binding.interruptorModoOscuro.isChecked = modoOscuro == "true" // Ajusta el switch de modo oscuro según preferencia
            binding.interruptorNotificaciones.isChecked = notificaciones == "true" // Ajusta el switch de notificaciones según preferencia

            FirebaseMessaging.getInstance().isAutoInitEnabled = notificaciones == "true" // Configura FCM según preferencia
        }
    }

    private fun cargarNombreDesdeFirestore() { // Función para cargar el nombre del usuario desde Firestore
        email?.let { correo -> // Si existe email, ejecuta el bloque
            db.collection("users").document(correo).get() // Obtiene el documento del usuario en la colección "users"
                .addOnSuccessListener { document -> // Listener si la consulta fue exitosa
                    if (document != null && document.exists()) { // Verifica si el documento existe
                        val nombre = document.getString("nombre") ?: "No establecido" // Obtiene el campo "nombre" o usa por defecto
                        binding.datoNombre.text = "Nombre: $nombre" // Muestra el nombre en la UI
                    } else {
                        binding.datoNombre.text = "Nombre: No establecido" // Si no existe, muestra "No establecido"
                    }
                }
                .addOnFailureListener { // Listener si ocurre un error
                    binding.datoNombre.text = "Nombre: Error al cargar" // Muestra mensaje de error
                }
        }
    }

    private suspend fun leerDato(clave: String): String { // Función suspendida para leer un dato de DataStore
        val key = stringPreferencesKey(clave) // Crea la clave
        return applicationContext.dataStore.data // Accede al flujo de datos de DataStore
            .catch { emit(emptyPreferences()) } // Si hay error, emite preferencias vacías
            .map { it[key] ?: "" } // Obtiene el valor de la clave o "" si no existe
            .first() // Recoge el primer valor emitido
    }
}

