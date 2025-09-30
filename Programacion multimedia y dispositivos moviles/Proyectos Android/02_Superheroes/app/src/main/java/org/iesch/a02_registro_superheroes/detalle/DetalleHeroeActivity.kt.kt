package org.iesch.a02_registro_superheroes.detalle

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.R
import org.iesch.a02_registro_superheroes.databinding.ActivityDetalleHeroeBinding
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding
import org.iesch.a02_registro_superheroes.model.Superheroe

class DetalleHeroeActivity : AppCompatActivity() {
    // para no cometer equivocaciones en las key me creo un companion object
    // es un objeto que define miembros estaticos
    companion object{
        const val ALTER_EGO="heroego"
        const val HERO_NAME="heroname"
        const val BIO="herobio"
        const val POWER="heropower"
        const val SUPERHERO_KEY="superheroe"
        const val SUPERHERO_FOTO="superheroe"
    }
    private lateinit var  binding: ActivityDetalleHeroeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetalleHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle=intent.extras!!
        val superheroe=if ( android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.TIRAMISU){
             intent.getParcelableExtra(SUPERHERO_KEY, Superheroe::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Superheroe>(SUPERHERO_KEY)
        }
        val bitmap=bundle.getParcelable<Bitmap>(SUPERHERO_FOTO)
        // Recibimos los objetos del Intent
        // Un objeto Bundle es un contenedor de datos que permite pasar datos entre actividades o Fragmentos
        /*val bundle = intent.extras!!
        val heroname = bundle.getString(HERO_NAME) ?:"No hay nombre"
        val heroego = bundle.getString(ALTER_EGO) ?:"No hay ego"
        val herobio = bundle.getString(BIO) ?:"No hay bio"
        val heropower = bundle.getFloat(POWER)*/
        // Asignamos los valores a los TextView
        /*binding.tvheronameresult.text=heroname
        binding.textViewego.text=heroego
        binding.tvBioResult.text=herobio
        binding.ratingBar.rating=heropower*///version antigua
        binding.tvheronameresult.text=superheroe?.nombre ?: "No hay nombre"
        binding.textViewego.text= superheroe?.alterego ?: "No hay ego"
        binding.tvBioResult.text=superheroe?.bio ?: "No hay bio"
        binding.ratingBar.rating=superheroe?.power ?: 0f

        binding.imageView.setImageBitmap(bitmap)
    }
}