package org.iesch.a03_menu_principal.superheroes.detalle
// Paquete donde se encuentra la actividad de detalle de superhéroes.

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.superheroes.RegistroSuperHeroeActivity
import org.iesch.a03_menu_principal.superheroes.model.SuperHeroe
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityDetalleHeroeBinding
// Importaciones necesarias para UI, binding, intents y modelo SuperHeroe.

class DetalleHeroeActivity : AppCompatActivity() {
    // Actividad que muestra el detalle de un superhéroe.

    // 3 - Para no cometer equivocaciones en las keys, me creo un companion object
    // Un companion object es un objeto que pertenece a una clase de Kotlin y permite definir miembros estáticos.
    companion object {
        /*
            const val HERO_NAME = "heroName"
            const val ALTER_EGO = "alter_ego"
            const val BIO = "bio"
            const val POWER = "power"
        */
        const val SUPERHEROE_KEY = "super_heroe" // Clave para pasar el objeto SuperHeroe por Intent.
        const val FOTO_KEY = "foto" // Clave para pasar la ruta de la foto por Intent.
    }

    private lateinit var binding: ActivityDetalleHeroeBinding
    // Binding para acceder a las vistas del layout.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge.
        binding = ActivityDetalleHeroeBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras!! // Recupera los extras enviados por el Intent.

        // Recupera el objeto SuperHeroe desde el Intent.
        val superHeroe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(SUPERHEROE_KEY, SuperHeroe::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<SuperHeroe>(SUPERHEROE_KEY)
        }

        // 8 - Eliminamos el Bitmap directo y obtenemos el String del directorio de ese bitmap.
        val bitmapDirectory = bundle.getString(FOTO_KEY) // Recupera la ruta del archivo de imagen.
        val bitmap = BitmapFactory.decodeFile(bitmapDirectory) // Decodifica la imagen desde el archivo.

        // Asigna los valores del superhéroe a la interfaz.
        binding.tvHeroNameResult.text = superHeroe?.nombre ?: "No hay nombre"
        binding.tvAlterEgoResult.text = superHeroe?.alterEgo ?: "No hay alter ego"
        binding.tvBioResult.text = superHeroe?.bio ?: "No hay Bio"
        binding.rbResultado.rating = superHeroe?.power ?: 0f // Asigna la valoración de poder.

        // Si existe un bitmap válido, lo muestra en el ImageView.
        if (bitmap != null) {
            binding.imageView.setImageBitmap(bitmap)
        }

        // Botón para volver al menú anterior.
        binding.btvolvermenu.setOnClickListener {
            finish()
        }

        // Botón para ir a la actividad de registro de superhéroes.
        binding.btguardarheroes.setOnClickListener {
            val intent = Intent(this, RegistroSuperHeroeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
