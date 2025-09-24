package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding
import org.iesch.a02_registro_superheroes.detalle.DetalleHeroeActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnGuardar.setOnClickListener {
            // nos creamos las variables necesarias para pasarlas al Intent
            val superheroName=binding.etHeroName.text.toString()
            val alterEgo=binding.etAlterego.text.toString()
            val bio=binding.etVio.text.toString()
            val power=binding.rbPower.rating
            iradetalleHeroe(superheroName,alterEgo,bio,power)
        }
    }
}

private fun RegisterActivity.iradetalleHeroe(nombre: String,ego: String,bio: String,power: Float) {
    //al pulsar el boton quiero ir a la otra pantalla
    // El intent tiene que tener claro el destino y el origen
    val intent= Intent(this, DetalleHeroeActivity::class.java)
    // Añado los valores al Intent con la funcion putExtra
    intent.putExtra("heroname",nombre)
    intent.putExtra("heroego",ego)
    intent.putExtra("herobio",bio)
    intent.putExtra("heropower",power)
    
    startActivity(intent)

}
