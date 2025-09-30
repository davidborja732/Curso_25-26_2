package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding
import org.iesch.a02_registro_superheroes.detalle.DetalleHeroeActivity
import org.iesch.a02_registro_superheroes.model.Superheroe

class RegisterActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityRegisterBinding
    // Creamos un avariable que va a manejar el resultado de haber hecho la foto
    private var heroBitmap: Bitmap?=null
    lateinit var heroImage: ImageView
    private val getContent=registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        //Esto devuelve un objeto bitmap
        bitmap ->
        heroBitmap =bitmap
        heroImage.setImageBitmap(heroBitmap)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnGuardar.setOnClickListener {
            // nos creamos las variables necesarias para pasarlas al Intent
            val superheroName=binding.etHeroName.text.toString()
            val alterEgo=binding.etAlterego.text.toString()
            val bio=binding.etVio.text.toString()
            val power=binding.rbResultado.rating
            // me creo un ibjeto superhero y lo envio a la funcion
            val superheroe= Superheroe(superheroName,alterEgo,bio,power)
            iradetalleHeroe(superheroe)
        }
        heroImage=binding.superheroImage
        binding.superheroImage.setOnClickListener {
            openCamera()
        }
    }
    private fun openCamera(){
        //abrimos la camara usando getcontent launch
        getContent.launch(null)
    }
}
private fun RegisterActivity.iradetalleHeroe(superheroe: Superheroe) {
    //al pulsar el boton quiero ir a la otra pantalla
    // El intent tiene que tener claro el destino y el origen
    // Añado los valores al Intent con la funcion putExtra
    /*intent.putExtra(DetalleHeroeActivity.HERO_NAME,superheroe.nombre)
    intent.putExtra(DetalleHeroeActivity.ALTER_EGO,superheroe.alterego)
    intent.putExtra(DetalleHeroeActivity.BIO,superheroe.bio)
    intent.putExtra(DetalleHeroeActivity.POWER,superheroe.power)*/
    val intent= Intent(this, DetalleHeroeActivity::class.java)
    intent.putExtra(DetalleHeroeActivity.SUPERHERO_KEY,superheroe)
    //añado un objeto bitmap al intent
    intent.putExtra(DetalleHeroeActivity.SUPERHERO_FOTO,heroImage.drawable.toBitmap())
    startActivity(intent)

}
