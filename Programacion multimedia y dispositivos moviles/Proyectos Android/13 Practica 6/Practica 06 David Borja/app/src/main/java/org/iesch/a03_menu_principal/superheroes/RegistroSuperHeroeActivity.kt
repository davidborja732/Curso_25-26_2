package org.iesch.a03_menu_principal.superheroes
// Paquete donde se encuentra la actividad de registro de superhéroes.

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.superheroes.detalle.DetalleHeroeActivity
import org.iesch.a03_menu_principal.superheroes.model.SuperHeroe
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityRegistroSuperHeroeBinding
import java.io.File
// Importaciones necesarias para permisos, cámara, archivos, UI y binding.

class RegistroSuperHeroeActivity : AppCompatActivity() {
    // Actividad que permite registrar un superhéroe y tomar una foto con la cámara.

    private lateinit var binding: ActivityRegistroSuperHeroeBinding
    // Binding para acceder a las vistas del layout.

    private lateinit var heroImage: ImageView
    // Referencia al ImageView donde se mostrará la foto del héroe.

    private var heroBitmap: Bitmap? = null
    // Variable para almacenar el bitmap de la foto tomada.

    private var picturePath = ""
    // Ruta absoluta del archivo de imagen.

    // Contrato para tomar una foto con la cámara.
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && picturePath.isNotEmpty()) {
                // Si la foto se tomó correctamente y tenemos ruta, decodificamos el archivo.
                heroBitmap = BitmapFactory.decodeFile(picturePath)
                heroImage.setImageBitmap(heroBitmap) // Mostramos la foto en el ImageView.
            }
        }

    // Contrato para solicitar permiso de cámara.
    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera() // Si se concede el permiso, abrimos la cámara.
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge.
        binding = ActivityRegistroSuperHeroeBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Registro Super Heroe"
        // Cambia el título de la barra superior.

        // Listener para el botón Guardar.
        binding.btnGuardar.setOnClickListener {
            val superHeroName = binding.etHeroName.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbPower.rating
            // Creamos objeto SuperHeroe con los datos introducidos.
            val superHeroe = SuperHeroe(superHeroName, alterEgo, bio, power)
            irADetalleHeroe(superHeroe) // Navegamos a la pantalla de detalle.
        }

        heroImage = binding.superheroImage
        // Inicializamos la referencia al ImageView.

        binding.superheroImage.setOnClickListener {
            checkCameraPermissionAndOpen() // Al pulsar la imagen, pedimos permiso y abrimos cámara.
        }
    }

    private fun checkCameraPermissionAndOpen() {
        // Verificamos si el permiso de cámara ya está concedido.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            openCamera() // Si está concedido, abrimos la cámara.
        } else {
            requestCameraPermission.launch(Manifest.permission.CAMERA) // Si no, lo solicitamos.
        }
    }

    private fun openCamera() {
        // Creamos archivo temporal para guardar la foto.
        val imageFile = createImageFile()
        val uri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider", // Autoridad definida en el manifest.
            imageFile
        )
        takePicture.launch(uri) // Lanzamos la cámara con la URI del archivo.
    }

    private fun createImageFile(): File {
        // Creamos archivo temporal en el directorio de imágenes de la app.
        val fileName = "superhero_image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath = imageFile.absolutePath // Guardamos la ruta absoluta.
        return imageFile
    }

    private fun irADetalleHeroe(superHeroe: SuperHeroe) {
        // Navegamos a la actividad de detalle del héroe.
        val intent = Intent(this, DetalleHeroeActivity::class.java)
        intent.putExtra(DetalleHeroeActivity.SUPERHEROE_KEY, superHeroe) // Pasamos objeto SuperHeroe.
        intent.putExtra(DetalleHeroeActivity.FOTO_KEY, picturePath) // Pasamos ruta de la foto.
        startActivity(intent)
        finish() // Cerramos la actividad actual.
    }
}
