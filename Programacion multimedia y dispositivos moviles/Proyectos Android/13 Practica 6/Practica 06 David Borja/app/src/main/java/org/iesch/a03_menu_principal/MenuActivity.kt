package org.iesch.a03_menu_principal
// Paquete principal de la aplicación.

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.remoteconfig.remoteConfig
import org.iesch.a03_menu_principal.Login.LoginActivity
import org.iesch.a03_menu_principal.ajustes.AjustesActivity
import org.iesch.a03_menu_principal.apirazas.RazasApiActivity
import org.iesch.a03_menu_principal.calculadora.MainActivityCalculadora
import org.iesch.a03_menu_principal.cine.ListaPeliculasActivity
import org.iesch.a03_menu_principal.edadcanina.EdadCaninaActivity
import org.iesch.a03_menu_principal.fragments.FragmentsActivity
import org.iesch.a03_menu_principal.mapas.MapasActivity
import org.iesch.a03_menu_principal.preguntas.MainActivityPregunta
import org.iesch.a03_menu_principal.superheroes.RegistroSuperHeroeActivity
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityMenuBinding
// Importaciones necesarias para Firebase, actividades y binding.

enum class ProviderType {
    EMAIL_PASSWORD,
    GOOGLE
}
// Enumeración para distinguir el tipo de proveedor de autenticación.

class MenuActivity : AppCompatActivity() {
    // Actividad principal del menú de la aplicación.

    private lateinit var binding: ActivityMenuBinding
    // Binding para acceder a las vistas del layout.

    private val db = FirebaseFirestore.getInstance()
    // Instancia de Firestore (aunque en este código no se usa directamente).

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Instala la pantalla de splash.
        configuracionRemota() // Configura RemoteConfig antes de cargar la UI.
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge.
        binding = ActivityMenuBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera datos del intent (usuario y proveedor).
        val bundle = intent.extras
        val email = bundle?.getString("usuario")
        val provider = bundle?.getString("provider")

        binding.tvBienvenida.text = email // Muestra el email en el TextView de bienvenida.

        // Configuración de listeners para cada botón del menú.
        binding.btnRazas.setOnClickListener { irARazasActivity() }
        binding.btnFragments.setOnClickListener { irAMenuFragments() }
        binding.btnEdadCanina.setOnClickListener { irAEdadCanina() }
        binding.btnSuperheroes.setOnClickListener { irASuperHeroes() }
        binding.btnSettings.setOnClickListener {
            irAConfiguracion(email.orEmpty(), provider.orEmpty())
        }
        binding.btnCine.setOnClickListener { irAPeliculas() }
        binding.btnMapas.setOnClickListener { irAmapasActivity() }
        binding.calculator.setOnClickListener {
            startActivity(Intent(this, MainActivityCalculadora::class.java))
        }
        binding.preguntitas.setOnClickListener {
            startActivity(Intent(this, MainActivityPregunta::class.java))
        }

        binding.imglogout.setOnClickListener {
            cerrarsesion() // Listener para cerrar sesión.
        }
    }

    private fun cerrarsesion() {
        // Muestra un diálogo de confirmación para cerrar sesión.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Quieres cerrar la sesión?")
        builder.setPositiveButton("Sí") { _, _ ->
            Firebase.auth.signOut() // Cierra sesión en FirebaseAuth.
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) // Vuelve a la pantalla de login.
            finish() // Cierra la actividad actual.
        }
        builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    private fun irAPeliculas() {
        // Navega a la actividad de lista de películas.
        startActivity(Intent(this, ListaPeliculasActivity::class.java))
    }

    private fun configuracionRemota() {
        // Configuración de Firebase RemoteConfig para cambiar color de fondo dinámicamente.
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            val rootView = findViewById<View>(R.id.main)
            if (task.isSuccessful) {
                val backgroundColor = Firebase.remoteConfig.getString("background_color")
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

    // Métodos de navegación a las distintas actividades.
    private fun irAMenuFragments() {
        startActivity(Intent(this, FragmentsActivity::class.java))
    }

    private fun irAEdadCanina() {
        startActivity(Intent(this, EdadCaninaActivity::class.java))
    }

    private fun irASuperHeroes() {
        startActivity(Intent(this, RegistroSuperHeroeActivity::class.java))
    }

    private fun irARazasActivity() {
        startActivity(Intent(this, RazasApiActivity::class.java))
    }

    private fun irAmapasActivity() {
        startActivity(Intent(this, MapasActivity::class.java))
    }

    private fun irAConfiguracion(email: String, provider: String) {
        // Navega a AjustesActivity pasando email y proveedor.
        val intent = Intent(this, AjustesActivity::class.java)
        intent.putExtra("usuario", email)
        intent.putExtra("provider", provider)
        startActivity(intent)
    }
}




