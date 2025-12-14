package org.iesch.a03_menu_principal.fragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import org.iesch.a03_menu_principal.fragments.fragmentos.HomeFragment
import org.iesch.a03_menu_principal.fragments.fragmentos.ProfileFragment
import org.iesch.a03_menu_principal.fragments.fragmentos.SettingsFragment
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityFragmentsBinding

class FragmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 6 Muestro el fragmento por defecto
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_inicio -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_perfil -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                R.id.menu_herramientas -> {
                    replaceFragment(SettingsFragment())
                    true
                }

                else -> false
            }
        }
    }

    // 1 - Me creo una funcion para reemplazar los fragments
    private fun replaceFragment(fragment: Fragment) {
        // 2 Creo las variables para manejar los fragmentos
        val fragmentManager = supportFragmentManager
        // 3 Creo la transaccion
        val fragmentTransaction = fragmentManager.beginTransaction()
        // 4 Reemplazamos el fragment que haya
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        // 5 Confirmamos la transaccion
        fragmentTransaction.commit()
    }
}