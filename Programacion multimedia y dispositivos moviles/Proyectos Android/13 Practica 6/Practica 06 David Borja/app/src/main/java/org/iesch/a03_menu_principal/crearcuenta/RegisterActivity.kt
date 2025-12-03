package org.iesch.a03_menu_principal.crearcuenta
// Paquete donde se encuentra la actividad de registro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import org.iesch.a03_menu_principal.Login.LoginActivity
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityRegisterBinding
// Importaciones necesarias para Firebase Auth, Firestore, RemoteConfig y binding

class RegisterActivity : AppCompatActivity() {
    // Definimos la actividad de registro que hereda de AppCompatActivity

    private lateinit var binding: ActivityRegisterBinding
    // Binding para acceder a las vistas del layout

    private lateinit var auth: FirebaseAuth
    // Instancia de FirebaseAuth para manejar autenticación

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    // Instancia de Firestore para guardar datos de usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge
        binding = ActivityRegisterBinding.inflate(layoutInflater) // Infla el layout con ViewBinding
        setContentView(binding.root) // Establece el layout como contenido

        auth = FirebaseAuth.getInstance() // Inicializa FirebaseAuth

        // Ajusta márgenes según barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configuracionRemota() // Configura RemoteConfig para cambiar color de fondo dinámicamente

        // Botón de registro
        binding.btnLoginFirebase.setOnClickListener {
            val nombre = binding.NombreUser.text?.toString()?.trim() // Obtiene nombre
            val email = binding.etUser.text?.toString()?.trim() // Obtiene email
            val password = binding.etPassword.text?.toString()?.trim() // Obtiene contraseña

            // Validación de campos vacíos
            if (!nombre.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                // Crea usuario en FirebaseAuth con email y contraseña
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Actualizamos el perfil de FirebaseAuth con el nombre
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(nombre)
                                .build()
                            auth.currentUser?.updateProfile(profileUpdates)

                            // Guardamos en Firestore con merge para no sobrescribir datos existentes
                            val userMap = hashMapOf(
                                "nombre" to nombre,
                                "email" to email
                            )
                            db.collection("users").document(email).set(userMap, SetOptions.merge())
                                .addOnSuccessListener {
                                    // Si se guarda correctamente, mostramos diálogo de éxito
                                    AlertDialog.Builder(this)
                                        .setTitle("Registro exitoso")
                                        .setMessage("Usuario creado correctamente")
                                        .setPositiveButton("Aceptar") { _, _ ->
                                            val intent = Intent(this, LoginActivity::class.java)
                                            startActivity(intent) // Navega a LoginActivity
                                            finish() // Cierra RegisterActivity
                                        }
                                        .setCancelable(false)
                                        .show()
                                }
                                .addOnFailureListener {
                                    // Si falla Firestore, mostramos error
                                    AlertDialog.Builder(this)
                                        .setTitle("Error")
                                        .setMessage("Error al guardar en Firestore")
                                        .setPositiveButton("Aceptar", null)
                                        .show()
                                }
                        } else {
                            // Si falla la creación de usuario en FirebaseAuth, mostramos error
                            AlertDialog.Builder(this)
                                .setTitle("Error")
                                .setMessage("Error al crear cuenta: ${task.exception?.message}")
                                .setPositiveButton("Aceptar", null)
                                .show()
                        }
                    }
            } else {
                // Si hay campos vacíos, mostramos alerta
                AlertDialog.Builder(this)
                    .setTitle("Campos vacíos")
                    .setMessage("Rellena todos los campos")
                    .setPositiveButton("Aceptar", null)
                    .show()
            }
        }

        // Botón para volver al menú anterior
        binding.tvVolverMenu.setOnClickListener {
            finish()
        }
    }

    private fun configuracionRemota() {
        // Configuración de Firebase RemoteConfig
        val configSettings: FirebaseRemoteConfigSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 5 // Intervalo mínimo de actualización
        }
        val firebaseConfig = com.google.firebase.Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
        firebaseConfig.setDefaultsAsync(mapOf("background_color" to "white")) // Valor por defecto

        // Descarga y activa configuración remota
        firebaseConfig.fetchAndActivate().addOnCompleteListener { task ->
            val rootView = findViewById<View>(R.id.main)
            if (task.isSuccessful) {
                val backgroundColor = firebaseConfig.getString("background_color")
                val color = if (backgroundColor.isNullOrEmpty()) "white" else backgroundColor
                try {
                    rootView.setBackgroundColor(android.graphics.Color.parseColor(color))
                } catch (e: IllegalArgumentException) {
                    rootView.setBackgroundColor(android.graphics.Color.WHITE)
                }
            } else {
                rootView.setBackgroundColor(android.graphics.Color.WHITE)
            }
        }
    }
}
