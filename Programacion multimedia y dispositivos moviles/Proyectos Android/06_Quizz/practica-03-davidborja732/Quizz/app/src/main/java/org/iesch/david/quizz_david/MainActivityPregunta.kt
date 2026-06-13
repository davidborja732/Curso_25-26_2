package org.iesch.david.quizz_david

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.david.quizz_david.databinding.ActivityMainPreguntaBinding
import org.iesch.david.quizz_david.detalle.MainActivityResultado
import org.iesch.david.quizz_david.model.Pregunta

class MainActivityPregunta : AppCompatActivity() {
    private lateinit var binding: ActivityMainPreguntaBinding

    private val preguntas = listOf(
        Pregunta("1/3", "10y = 70", "y = 7", "y = 23", "opcion1"),
        Pregunta("2/3", "5y = 45", "y = 9", "y = 8", "opcion1"),
        Pregunta("3/3", "3y = 21", "y = 6", "y = 7", "opcion2")
    )

    private var preguntaActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPreguntaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val indiceRecibido = intent.getIntExtra("indice", 0)
        preguntaActual = indiceRecibido

        mostrarPregunta()

        binding.btEnviar.setOnClickListener {
            val idseleccionado = binding.rgOpciones.checkedRadioButtonId

            if (idseleccionado != -1) {
                var idrespuesta = ""

                if (idseleccionado == binding.opcion1.id) {
                    idrespuesta = "opcion1"
                } else if (idseleccionado == binding.opcion2.id) {
                    idrespuesta = "opcion2"
                } else {
                    idrespuesta = ""
                }

                val respuestaCorrecta = preguntas[preguntaActual].respuestaCorrecta
                var correcta = false

                if (idrespuesta == respuestaCorrecta) {
                    correcta = true
                } else {
                    correcta = false
                }

                val intent = Intent(this, MainActivityResultado::class.java)
                intent.putExtra(MainActivityResultado.OPCION_KEY, idrespuesta)
                intent.putExtra(MainActivityResultado.RESULTADO_KEY, correcta)
                intent.putExtra(MainActivityResultado.INDICE_KEY, preguntaActual)
                startActivity(intent)
            } else {
                val mensaje = getString(R.string.toast_select_option)
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun mostrarPregunta() {
        val pregunta = preguntas[preguntaActual]

        val textoOperacion = pregunta.operacion
        val textoEcuacion = pregunta.ecuacion
        val textoOpcion1 = pregunta.opcion1
        val textoOpcion2 = pregunta.opcion2

        binding.tvOperacion.text = textoOperacion
        binding.tvEquacion.text = textoEcuacion
        binding.opcion1.text = textoOpcion1
        binding.opcion2.text = textoOpcion2
    }
}





