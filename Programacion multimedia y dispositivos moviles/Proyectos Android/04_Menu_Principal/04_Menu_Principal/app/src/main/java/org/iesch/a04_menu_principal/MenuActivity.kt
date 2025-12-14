package org.iesch.a04_menu_principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a04_menu_principal.apiRazas.RazasActivity
import org.iesch.a04_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a04_menu_principal.fragments.FragmentoActivity
import org.iesch.a04_menu_principal.settings.SettingsActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnApi.setOnClickListener {
            irArazasActvity()
        }
        binding.btnApi2.setOnClickListener {
            irAmenusfragmento()
        }
        binding.btnPreguntas2.setOnClickListener {
            irArConfiguracionActvity()
        }
        binding.btnPreguntas.setOnClickListener {
            irArPeliculasActvity();
        }

    }

    private fun irAmenusfragmento(){
        val irfragmento1= Intent(this, FragmentoActivity::class.java)
        startActivity(irfragmento1)
    }
    private fun irArazasActvity() {
        val irraza= Intent(this, RazasActivity::class.java)
        startActivity(irraza)
    }
    private fun irArConfiguracionActvity() {
        val irraza= Intent(this, SettingsActivity::class.java)
        startActivity(irraza)
    }
    private fun irArPeliculasActvity() {
        val irraza= Intent(this, PeliculasActivity::class.java)
        startActivity(irraza)
    }


}