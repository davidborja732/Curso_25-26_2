package org.iesch.a03_menu_principal.preguntas
// Paquete donde se encuentra la actividad de resultados.

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.MenuActivity
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityResultadoBinding
// Importaciones necesarias para UI, binding, intents y navegación.

class MainActivityResultado : AppCompatActivity() {
    // Actividad que muestra el resultado de la respuesta seleccionada en MainActivityPregunta.

    companion object {
        // Claves para pasar datos entre actividades mediante Intent.
        const val OPCION_KEY = "idrespuesta"
        const val RESULTADO_KEY = "resultado"
        const val INDICE_KEY = "indice"
    }

    private lateinit var binding: ActivityResultadoBinding
    // Binding para acceder a las vistas del layout.

    private var estadoFinal = false
    // Indica si hemos llegado al final del cuestionario y mostrado el mensaje final.

    private var indice = 0
    // Índice de la pregunta actual.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera datos enviados desde MainActivityPregunta.
        val resultado = intent.getBooleanExtra(RESULTADO_KEY, false) // Si la respuesta fue correcta.
        indice = intent.getIntExtra(INDICE_KEY, 0) // Índice de la pregunta.

        // Muestra mensaje según resultado.
        if (resultado) {
            binding.tvBienmal.text = getString(R.string.resultado_correcto)
        } else {
            binding.tvBienmal.text = getString(R.string.resultado_incorrecto)
        }

        // Configura el texto del botón según si es la última pregunta.
        if (indice == 2) {
            binding.btResultado.text = getString(R.string.bt_resultado) // "Finalizar"
        } else {
            binding.btResultado.text = getString(R.string.resultado_siguiente) // "Siguiente"
        }

        // Botón para volver al menú principal.
        binding.btMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        // Botón para avanzar en el cuestionario o reiniciar.
        binding.btResultado.setOnClickListener {
            if (indice == 2 && !estadoFinal) {
                // Si estamos en la última pregunta y aún no hemos mostrado el fin.
                binding.tvBienmal.text = getString(R.string.resultado_finusu) // "Has terminado"
                binding.btResultado.text = getString(R.string.resultado_reiniciar) // Cambia a "Reiniciar"
                estadoFinal = true
                binding.btMenu.visibility = View.VISIBLE // Muestra botón de menú.
            } else if (indice == 2 && estadoFinal) {
                // Si ya hemos mostrado el fin y pulsamos reiniciar.
                val intent = Intent(this, MainActivityPregunta::class.java)
                intent.putExtra("indice", 0) // Reinicia desde la primera pregunta.
                startActivity(intent)
                finish()
            } else {
                // Si no es la última pregunta, avanzamos a la siguiente.
                val intent = Intent(this, MainActivityPregunta::class.java)
                intent.putExtra("indice", indice + 1) // Pasamos al siguiente índice.
                startActivity(intent)
                finish()
            }
        }
    }
}
