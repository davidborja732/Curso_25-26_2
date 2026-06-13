package org.iesch.a03_menu_principal.superheroes.detalle

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityDetalleHeroeBinding
import org.iesch.a03_menu_principal.superheroes.RegistroSuperHeroeActivity
import org.iesch.a03_menu_principal.superheroes.model.SuperHeroe


class DetalleHeroeActivity : AppCompatActivity() {

    // 3 - PAra no cometer equivocaciones en las keys, me creo un companion object
    // Un companion object es un objeto que pertenece a una clase de Kotlin y permite definir miembros estáticos
    companion object {/*
        const val HERO_NAME = "heroName"
        const val ALTER_EGO = "alter_ego"
        const val BIO = "bio"
        const val POWER = "power"*/
        const val SUPERHEROE_KEY = "super_heroe"
        const val FOTO_KEY = "foto"
    }
    private lateinit var binding: ActivityDetalleHeroeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Detalle Super Heroe"
        val bundle = intent.extras!!

        val superHeroe = if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(SUPERHEROE_KEY, SuperHeroe::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<SuperHeroe>(SUPERHEROE_KEY)
        }
        //val bitmap = bundle.getParcelable<Bitmap>(FOTO_KEY)!!
        // 8 - Eliminamos el Bitmap y obtenemos el String del directorio de ese bitmap
        val bitmapDirectory = bundle.getString(FOTO_KEY)
        val bitmap = BitmapFactory.decodeFile(bitmapDirectory)


        binding.tvHeroNameResult.text = superHeroe?.nombre ?: "No hay nombre"
        binding.tvAlterEgoResult.text = superHeroe?.alterEgo ?: "No hay alter ego"
        binding.tvBioResult.text = superHeroe?.bio ?: "No hay Bio"
        binding.rbResultado.rating = superHeroe?.power ?: 0f

        if ( bitmap != null ){
            binding.imageView.setImageBitmap(bitmap)
        }

        binding.btnVolverRegistro.setOnClickListener {
            val intent = Intent(this, RegistroSuperHeroeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}