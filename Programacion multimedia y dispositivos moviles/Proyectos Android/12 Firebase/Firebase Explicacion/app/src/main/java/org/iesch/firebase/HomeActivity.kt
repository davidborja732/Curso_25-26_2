package org.iesch.firebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.iesch.firebase.databinding.ActivityHomeBinding

enum class ProviderType{
    EMAIL_PASSWORD,
    GOOGLE
}
class HomeActivity : AppCompatActivity() {
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
    }
}