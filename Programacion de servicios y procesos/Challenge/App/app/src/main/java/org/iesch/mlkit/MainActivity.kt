package org.iesch.mlkit

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import org.iesch.mlkit.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private val adaptador1: ArrayAdapter<*>? = null
    private var lv1: ListView? = null
    private lateinit var heroImage: ImageView
    // Referencia al ImageView donde se mostrará la foto del héroe.

    private var heroBitmap: Bitmap? = null
    // Variable para almacenar el bitmap de la foto tomada.
    private var heroBitmapAnalizar: Bitmap? = null
    private var picturePath = ""
    // Ruta absoluta del archivo de imagen.
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        heroImage = binding.imagenanaliazar
        // Inicializamos la referencia al ImageView.

        binding.imagenanaliazar.setOnClickListener {
            checkCameraPermissionAndOpen() // Al pulsar la imagen, pedimos permiso y abrimos cámara.
        }
        binding.btnComprobar.setOnClickListener {
            obtenerbitmap(heroBitmapAnalizar)
        }


    }

    // Contrato para tomar una foto con la cámara.
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && picturePath.isNotEmpty()) {
                // Si la foto se tomó correctamente y tenemos ruta, decodificamos el archivo.
                heroBitmap = BitmapFactory.decodeFile(picturePath)
                heroBitmapAnalizar=heroBitmap
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
    private fun obtenerbitmap(bitmap: Bitmap?){
        val image: InputImage = bitmap?.let { InputImage.fromBitmap(it, 0) }!!
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        labeler.process(image)
            .addOnSuccessListener { labels ->
                val datosParaMostrar = labels.map { label ->
                    "${label.text} ${(label.confidence * 100).toInt()}%"
                }.toMutableList()
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    datosParaMostrar
                )

                binding.tvDatos.adapter = adapter


            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Algo fallo al procesar la imagen ", Toast.LENGTH_SHORT).show()
            }
    }
}