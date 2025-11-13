package org.iesch.firebase

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import org.iesch.firebase.databinding.ActivityHomeBinding
import androidx.core.graphics.toColorInt
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import kotlin.collections.hashMapOf

enum class ProviderType{
    EMAIL_PASSWORD,
    GOOGLE
}
class HomeActivity : AppCompatActivity() {
    //Creamos una instancia en una base de datos, asi ya la tenemos conectada
    private val db= FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // recuperamos los datos
        val bundle=intent.extras
        val email=bundle?.getString("usuario")
        val provider=bundle?.getString("provider")
        // Mostramos los valores
        binding.emailTextView.text=email.toString()
        binding.metodoTextView.text=provider.toString()
        // Listener para cerrar sesion
        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
        // Recuperamos el RemoteConfig
        configuracionremota()
        binding.GuardarButton.setOnClickListener {
            // Creamos la estructura para guardar datos la clave para cada usuario es su email
            db.collection("usuarios").document(email.toString()).set(
                hashMapOf(
                    "provider" to provider,
                    "email" to email,
                    "direccion" to binding.adressEditText.text.toString(),
                    "telefono" to binding.phoneEditText.text.toString()
                )
            )
        }
        binding.recuperarButton.setOnClickListener {
            // Recupero datos firebase
            db.collection("usuarios").document(email.toString()).get().addOnCompleteListener {
                datos ->
                if (datos != null){
                    binding.adressEditText.setText(datos.result.getString("direccion"))
                    binding.phoneEditText.setText(datos.result.getString("telefono"))

                }
            }
        }
        binding.EliminarButton.setOnClickListener {
            db.collection("usuarios").document(email.toString()).delete()
        }
        binding.EspecialButton.setOnClickListener {
            val intent= Intent(this, ListaTareasActivity::class.java)
            startActivity(intent)
        }
    }


    private fun configuracionremota(){
        binding.EspecialButton.visibility= View.INVISIBLE
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
            //val colorDeFondo= Firebase.remoteConfig.getString("color_bg")
            val showOptionalButton= Firebase.remoteConfig.getBoolean("boton_opcional")
            val textoboton= Firebase.remoteConfig.getString("texto_opcional")
            if (showOptionalButton){
                binding.EspecialButton.visibility= View.VISIBLE
                binding.EspecialButton.text=textoboton
            }
            //binding.root.setBackgroundColor("R.color."+colorDeFondo")
        }
    }
}