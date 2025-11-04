package org.iesch.firebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.iesch.firebase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    // Inicializar Analytic
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    // Autenticacion
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarAnalytics()
        // Iniciamos Firebase Auth
        auth = Firebase.auth
        // Configuramos los listener de los botones
        binding.loginButton.setOnClickListener {
            // Comprobamos si hemos introducido usuartio y contraseña
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()){
                // Si no esta vacio intentamos iniciar sesion
                auth.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
                    // Añadimos un listener si el usuario esta logueado correctamente
                    .addOnCompleteListener { autenticacion ->
                        if (autenticacion.isSuccessful){
                            // El usuario se loguea correctamente
                            mostrarHomeActivity(binding.emailEditText.text.toString(), ProviderType.EMAIL_PASSWORD.toString())
                        }else{
                            // Ha habido algun error
                            avisoUsuario("Fallo en la autenticacion","Logueo incorrecto")
                        }
                    }
            }else{
                avisoUsuario("Rellena los campos","Campos vacios")
                //Toast.makeText(this, "Rellene ambos campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registerButton.setOnClickListener {
            // Comprobamos si hemos introducido usuartio y contraseña
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()){
                // Si no esta vacio intentamos registrar
                auth.createUserWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
                    // Añadimos un listener si el usuario esta registrado correctamente
                    .addOnCompleteListener { registro ->
                        if (registro.isSuccessful){
                            // El usuario se registra correctamente
                            avisoUsuario("usuario registrado","Registro correcto")
                        }else{
                            // Ha habido algun error
                            avisoUsuario("Fallo en el registro","Registro incorrecto")
                        }
                    }
            }else{
                avisoUsuario("Rellena los campos","Campos vacios")
                //Toast.makeText(this, "Rellene ambos campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun avisoUsuario(mensaje: String,titulo: String) {
        // Mostrar Error mediante AlertDialog
        val builder= AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar",null)
        val dialog=builder.create()
        dialog.show()
    }

    private fun mostrarHomeActivity(usuario: String,provider: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("usuario",usuario)
        intent.putExtra("provider",provider)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val usuarioactual = auth.currentUser
        if (usuarioactual != null) {
            val email=usuarioactual.email
            mostrarHomeActivity(email.toString(), ProviderType.EMAIL_PASSWORD.toString())
            finish()
        }
    }

    private fun iniciarAnalytics() {
        firebaseAnalytics= Firebase.analytics;
        val bundle= Bundle()
        bundle.putString("mensaje","Integracion con firebase realizada correctamente")
        firebaseAnalytics.logEvent("LoginScreen",bundle)
    }
}