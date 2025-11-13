package org.iesch.firebase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.messaging.messaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.launch
import org.iesch.firebase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.e("FCM", "Concedido permiso")
        } else {
            Log.e("FCM", "No concedido permiso")
        }
    }
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
        binding.loginGoogleButton.setOnClickListener {
            logueocongoogle()
        }
        // Solicitar permisos de notificacion
        solicitarPermisosPush()
        notificacionesPush()
        // Configuracion remota
            configuracionRemota();
        // Me puedo suscribir a temas
        Firebase.messaging.subscribeToTopic("RealValladolid")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM", "Suscrito al Real Valladolid")
                } else {
                    avisoUsuario("NO se ha suscrito", "Suscripcion Real Valladolid")
                }
            }
    }

    private fun configuracionRemota() {
        // Lo recomendable es escribir un valor por defecto para todos los valores remotos
        val configSettings: FirebaseRemoteConfigSettings= remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        // Obtenemos la instancia de RemoteConfig
        val firebaseConfig= Firebase.remoteConfig
        // Aplicamos la configuracion a remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
        // Establecemos los valores por defecto en caso que fale la obtencion de valores remotos
        firebaseConfig.setDefaultsAsync(mapOf(
            "boton_opcional" to false,
            "texto_opcional" to "Texto por defecto",
            "color_bg" to "bg_1"
        ))
    }

    private fun solicitarPermisosPush() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun notificacionesPush() {
        // Obtenemos token de registro
        Firebase.messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.e("FCM", "Token de registro:$token")
            } else {
                Log.e("FCM", "Error al obtener el token de registro")
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

    private fun mostrarHomeActivity(usuario: String, provider: String) {
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
    private fun logueocongoogle() {
        // Vamos a crearlo siguiendo la documentacion oficial
        // Instanciamos solicitud de incio con google
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.web_client))
            .setFilterByAuthorizedAccounts(false)
            .build()
        //Generamos la solicitud de credenciales
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        // Obtenemos el CredentialManager y lanzamos las solicitudes
        lifecycleScope.launch {
            try {
                val credentialManager = CredentialManager.create(this@LoginActivity)
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity
                )
                handleSignIn(result.credential)
            } catch (e: Exception) {
                Log.e("DAM", "Error al obtener las credenciales")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Sign in to Firebase with using the token
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w("DAM", "Credential is not of type Google ID!")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // AQui ya nos hemos logueado con google
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("DAM", "signInWithCredential:success")
                    val user = auth.currentUser
                    mostrarHomeActivity(user?.email.toString(), ProviderType.GOOGLE.toString())
                } else {
                    // If sign in fails, display a message to the user
                    Log.w("DAM", "signInWithCredential:failure", task.exception)
                    Log.e("DAM", "Error al lofuearse con google")
                }
            }
    }

}