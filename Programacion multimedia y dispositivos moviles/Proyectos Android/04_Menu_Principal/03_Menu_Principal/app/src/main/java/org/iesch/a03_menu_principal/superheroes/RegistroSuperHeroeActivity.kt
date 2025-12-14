package org.iesch.a03_menu_principal.superheroes

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
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityRegistroSuperHeroeBinding
import org.iesch.a03_menu_principal.superheroes.detalle.DetalleHeroeActivity
import org.iesch.a03_menu_principal.superheroes.model.SuperHeroe
import java.io.File


class RegistroSuperHeroeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroSuperHeroeBinding
    private lateinit var heroImage: ImageView


    private var heroBitmap: Bitmap? = null
    // 1 - Cambiamos el TakePicturePreview por TakePicture
    private  var picturePath = ""
    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()){
        //Ahora en lugar de un bitmap nos va a devolver un booleano si la toma de la foto es exitosa
            success ->
        if ( success && picturePath.isNotEmpty() ){
            // Culquier imagen del directorio la podemos convertir en un bitmap
            heroBitmap = BitmapFactory.decodeFile(picturePath)
            // Pintamos la imagen en el cuadradito
            heroImage.setImageBitmap(heroBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroSuperHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Registro Super Heroe"

        binding.btnGuardar.setOnClickListener {

            val superHeroName = binding.etHeroName.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbPower.rating

            val superHeroe = SuperHeroe(superHeroName, alterEgo, bio, power)
            irADetalleHeroe( superHeroe )

        }


        heroImage = binding.superheroImage
        binding.superheroImage.setOnClickListener {
            openCamera()
        }
    }
    private fun openCamera() {
        // 2 - Ahora a quí debemos crear un path temporal para guardar la imagen
        val imageFile = createImageFile()
        // 4-  Ahora ya tenemos el File, pero lo que necesitamos es el uri
        // Como estamospor encima de la SDK 24 obtendremos el Uri a través de FileProvider
        // FileProvider lo que hace es compartir el file con otras aplicaciones de forma segura
        val uri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            imageFile
        )
        // 5 - Ahora le pasamos el uri a la funcion launcher
        getContent.launch(uri)
    }

    //3 - Esta función crea un File y de ese File recupreraremos el uri.
    private fun createImageFile() : File {
        val fileName = "superhero_image"
        // Esto será el directorio donde vamos a almcenar la imagen. Por defecto es DIRECTORY_PICTURES
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES )
        // creamos nuestro file, y aqui nos pide el nombre, el formato, y el directorio
        val imageFile = File.createTempFile(fileName, ".jpg", fileDirectory )
        // Ahora ya podemos guardar el path en la variable global
        picturePath = imageFile.absolutePath
        return imageFile
    }

    private fun irADetalleHeroe( superHeroe: SuperHeroe ) {

        val intent = Intent(this, DetalleHeroeActivity::class.java)
        intent.putExtra(DetalleHeroeActivity.SUPERHEROE_KEY, superHeroe)
        // 7 . Pasamos solamente el picturePath
        intent.putExtra(DetalleHeroeActivity.FOTO_KEY, picturePath)
        startActivity(intent)
        finish()
    }
}