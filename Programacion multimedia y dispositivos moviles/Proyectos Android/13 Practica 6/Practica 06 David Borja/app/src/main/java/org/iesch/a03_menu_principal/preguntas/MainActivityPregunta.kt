package org.iesch.a03_menu_principal.preguntas
// Paquete donde se encuentra la actividad de preguntas.

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityMainPreguntaBinding
// Importaciones necesarias para UI, binding, intents y mensajes Toast.

class MainActivityPregunta : AppCompatActivity() {
    // Actividad que muestra preguntas con opciones y permite responder.

    private lateinit var binding: ActivityMainPreguntaBinding
    // Binding para acceder a las vistas del layout.

    private val preguntas = listOf(
        // Lista de preguntas con sus datos: número, ecuación, opciones y respuesta correcta.
        Pregunta("1/3", "10y = 70", "y = 7", "y = 23", "opcion1"),
        Pregunta("2/3", "5y = 45", "y = 9", "y = 8", "opcion1"),
        Pregunta("3/3", "3y = 21", "y = 6", "y = 7", "opcion2")
    )

    private var preguntaActual = 0
    // Índice de la pregunta actual que se está mostrando.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPreguntaBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val indiceRecibido = intent.getIntExtra("indice", 0)
        // Recupera el índice de la pregunta desde el Intent (por defecto 0).
        preguntaActual = indiceRecibido

        mostrarPregunta() // Muestra la pregunta correspondiente.

        // Listener para el botón "Enviar".
        binding.btEnviar.setOnClickListener {
            val idseleccionado = binding.rgOpciones.checkedRadioButtonId
            // Obtiene el ID de la opción seleccionada en el RadioGroup.

            if (idseleccionado != -1) {
                // Si se seleccionó alguna opción.
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

                // Compara la respuesta seleccionada con la correcta.
                if (idrespuesta == respuestaCorrecta) {
                    correcta = true
                } else {
                    correcta = false
                }

                // Crea un Intent para ir a la actividad de resultados.
                val intent = Intent(this, MainActivityResultado::class.java)
                intent.putExtra(MainActivityResultado.OPCION_KEY, idrespuesta) // Opción seleccionada.
                intent.putExtra(MainActivityResultado.RESULTADO_KEY, correcta) // Si fue correcta o no.
                intent.putExtra(MainActivityResultado.INDICE_KEY, preguntaActual) // Índice de la pregunta.
                startActivity(intent) // Inicia la actividad de resultados.
                finish() // Cierra la actividad actual.
            } else {
                // Si no se seleccionó ninguna opción, muestra un Toast de aviso.
                val mensaje = getString(R.string.toast_select_option)
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun mostrarPregunta() {
        // Muestra los datos de la pregunta actual en la interfaz.
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
