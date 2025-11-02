package org.iesch.a04_menu_principal.fragments

import androidx.*
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import org.iesch.a04_menu_principal.R
import org.iesch.a04_menu_principal.databinding.ActivityFragmentoBinding
import org.iesch.a04_menu_principal.fragments.fragmentos.HomeFragment
import org.iesch.a04_menu_principal.fragments.fragmentos.ProfileFragment
import org.iesch.a04_menu_principal.fragments.fragmentos.SettingsFragment


class FragmentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityFragmentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title="Fragmentos"
        // muestro el fragmento por defecto
        replaceFragment(HomeFragment())
        binding.Bottomnavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menu_inicio -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.perfil -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.herramientas -> {
                    replaceFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }
    // Me creo una funcion para reemplazar los fragments
    private fun replaceFragment(fragment:Fragment){
        // creo las variables para manejar los fragmentos
        val fragmentmanager=supportFragmentManager
        // Creo la transaccion
        val transaction=fragmentmanager.beginTransaction()
        // Lo que tengo que hacer es reemplazar el fragment
        transaction.replace(R.id.frameLayout,fragment)
        // Confirmamos la transaccion
        transaction.commit()
    }
}