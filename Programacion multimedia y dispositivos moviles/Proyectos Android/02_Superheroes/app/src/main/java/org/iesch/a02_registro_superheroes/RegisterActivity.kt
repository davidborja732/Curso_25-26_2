package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding
import org.iesch.a02_registro_superheroes.detalle.DetalleHeroeActivity
import org.iesch.a02_registro_superheroes.model.Superheroe
import java.io.File

class RegisterActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityRegisterBinding
    // Creamos un avariable que va a manejar el resultado de haber hecho la foto
    private var heroBitmap: Bitmap?=null

    // cambiamos el TakepicturePreview por takepicture
    var picturePatch = ""
    lateinit var heroImage: ImageView
    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        // Ahora en lugar de un bitmat nos devolvera un booleano si es exitosa
            success ->
        if (success && picturePatch.isNotEmpty()) {
            heroBitmap = BitmapFactory.decodeFile(picturePatch)
            heroImage.setImageBitmap(heroBitmap)
        }
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
        val imageFile = createImageFile()
        val uri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            imageFile
        )
        getContent.launch(uri)
    }

    private fun createImageFile(): File {
        val filename = "superheroe_image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(filename, ".jpg", fileDirectory)
        picturePatch = imageFile.absolutePath
        return imageFile
    }
}
private fun RegisterActivity.iradetalleHeroe(superheroe: Superheroe) {
    val intent= Intent(this, DetalleHeroeActivity::class.java)
    intent.putExtra(DetalleHeroeActivity.SUPERHERO_KEY,superheroe)
    intent.putExtra(DetalleHeroeActivity.SUPERHERO_FOTO, picturePatch)
    startActivity(intent)

}
