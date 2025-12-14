package org.iesch.a03_menu_principal.Login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.messaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.MenuActivity
import org.iesch.a03_menu_principal.ProviderType
import org.iesch.a03_menu_principal.crearcuenta.RegisterActivity
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    // Actividad de login que hereda de AppCompatActivity.

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        // Callback que se ejecuta cuando el usuario concede o deniega el permiso de notificaciones.
        if (isGranted) {
            Log.e("FCM", "Concedido permiso")
        } else {
            Log.e("FCM", "No concedido permiso")
        }
    }

    private lateinit var auth: FirebaseAuth // Instancia de FirebaseAuth para autenticación.
    private lateinit var binding: ActivityLoginBinding // Binding para acceder al layout.
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance() // Instancia de Firestore.

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Instala la pantalla de splash.
        configuracionRemota() // Configura RemoteConfig antes de cargar la UI.
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge.
        binding = ActivityLoginBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        auth = Firebase.auth // Inicializa FirebaseAuth.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        solicitarPermisosPush() // Solicita permisos de notificaciones en Android 13+.
        notificacionesPush() // Obtiene token de FCM.

        // Botón de login con email y contraseña.
        binding.btnLoginFirebase.setOnClickListener {
            val user = binding.etUser.text?.toString()
            val password = binding.etPassword.text?.toString()

            if (!user.isNullOrEmpty() && !password.isNullOrEmpty()) {
                auth.signInWithEmailAndPassword(user, password)
                    .addOnCompleteListener { autenticacion ->
                        if (autenticacion.isSuccessful) {
                            val email = user
                            // Recuperamos nombre desde Firestore en lugar de displayName.
                            cargarUsuarioDesdeFirestore(email, ProviderType.EMAIL_PASSWORD.toString())
                        } else {
                            avisoUsuario("Logueo incorrecto", "Fallo en la autenticación")
                        }
                    }
            } else {
                avisoUsuario("Campos vacíos", "Rellena todos los campos")
            }
        }

        // Botón para ir a la pantalla de registro.
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Botón de login con Google.
        binding.btnLoginGoogle.setOnClickListener {
            logueocongoogle()
        }
    }

    private fun irAMenu(usuario: String, provider: String) {
        // Navega a MenuActivity pasando email y proveedor.
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("usuario", usuario)
        intent.putExtra("provider", provider)
        startActivity(intent)
        finish()
    }

    public override fun onStart() {
        super.onStart()
        val usuarioactual = auth.currentUser
        usuarioactual?.let {
            val email = it.email ?: ""
            val provider =
                it.providerData.firstOrNull()?.providerId ?: ProviderType.EMAIL_PASSWORD.toString()
            // Recuperamos nombre desde Firestore.
            cargarUsuarioDesdeFirestore(email, provider)
        }
    }

    private fun avisoUsuario(mensaje: String, titulo: String) {
        // Muestra un AlertDialog con título y mensaje.
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun solicitarPermisosPush() {
        // Solicita permiso de notificaciones en Android 13+.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun notificacionesPush() {
        // Obtiene token de FCM y lo muestra en log.
        Firebase.messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.e("FCM", "Token de registro:$token")
            } else {
                Log.e("FCM", "Error al obtener el token de registro")
            }
        }
    }

    private fun configuracionRemota() {
        // Configuración de Firebase RemoteConfig.
        val configSettings: FirebaseRemoteConfigSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 5
        }
        val firebaseConfig = Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
        firebaseConfig.setDefaultsAsync(mapOf("background_color" to "white"))

        firebaseConfig.fetchAndActivate().addOnCompleteListener { task ->
            val rootView = findViewById<View>(R.id.main)
            if (task.isSuccessful) {
                val backgroundColor = firebaseConfig.getString("background_color")
                val color = if (backgroundColor.isNullOrEmpty()) "white" else backgroundColor
                try {
                    rootView.setBackgroundColor(android.graphics.Color.parseColor(color))
                } catch (e: IllegalArgumentException) {
                    rootView.setBackgroundColor(android.graphics.Color.WHITE)
                }
            } else {
                rootView.setBackgroundColor(android.graphics.Color.WHITE)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        // Autenticación con Google usando idToken.
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val nombre = user?.displayName ?: ""
                    val email = user?.email ?: ""
                    guardarUsuarioEnFirestore(nombre, email, ProviderType.GOOGLE.toString())
                    irAMenu(email, ProviderType.GOOGLE.toString())
                } else {
                    avisoUsuario("Error al iniciar sesión con Google", "Fallo en la autenticación")
                    Log.e("DAM", "Error al loguearse con Google", task.exception)
                }
            }
    }

    private fun handleSignIn(credential: Credential) {
        // Maneja credenciales obtenidas de Google.
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w("DAM", "Credential is not of type Google ID!")
        }
    }

    private fun logueocongoogle() {
        // Configura opciones de login con Google.
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.web_client))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val credentialManager = CredentialManager.create(this@LoginActivity)
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity
                )
                handleSignIn(result.credential)
            } catch (e: Exception) {
                avisoUsuario("Error", "No se pudo iniciar sesión con Google")
                Log.e("DAM", "Error al obtener las credenciales", e)
            }
        }
    }

    private fun guardarUsuarioEnFirestore(nombre: String, email: String, provider: String) {
        // Guarda usuario en Firestore junto con token de FCM.
        Firebase.messaging.token.addOnCompleteListener { tokenTask ->
            val token = if (tokenTask.isSuccessful) tokenTask.result else ""
            val userMap = hashMapOf(
                "nombre" to nombre,
                "email" to email,
                "provider" to provider,
                "token" to token
            )
            // Usamos merge para no sobrescribir datos previos.
            db.collection("users").document(email).set(userMap, SetOptions.merge())
        }
    }

    private fun cargarUsuarioDesdeFirestore(email: String, provider: String) {
        // Recupera usuario desde Firestore y actualiza token.
        db.collection("users").document(email).get()
            .addOnSuccessListener { doc ->
                val nombre = doc.getString("nombre") ?: ""
                guardarUsuarioEnFirestore(nombre, email, provider) // actualizamos token
                irAMenu(email, provider)
            }
            .addOnFailureListener {
                irAMenu(email, provider) // fallback si no existe documento
            }
    }
}




