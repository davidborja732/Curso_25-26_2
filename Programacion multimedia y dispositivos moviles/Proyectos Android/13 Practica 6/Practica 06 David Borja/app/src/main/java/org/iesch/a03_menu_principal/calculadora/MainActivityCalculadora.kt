package org.iesch.a03_menu_principal.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityMainCalculadoraBinding

class MainActivityCalculadora : AppCompatActivity() {
    // Definimos la actividad principal de la calculadora, que hereda de AppCompatActivity.

    private lateinit var binding: ActivityMainCalculadoraBinding
    // Variable para acceder al layout mediante ViewBinding.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase padre.
        enableEdgeToEdge() // Activa diseño edge-to-edge para que la UI ocupe toda la pantalla.

        binding = ActivityMainCalculadoraBinding.inflate(layoutInflater)
        // Infla el layout usando ViewBinding.

        setContentView(binding.root)
        // Establece el layout como contenido de la actividad.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Listener para el botón de sumar.
        binding.btnSumar.setOnClickListener {
            val num1 = binding.numero1.text.toString() // Obtiene el texto del primer número.
            val num2 = binding.numero2.text.toString() // Obtiene el texto del segundo número.
            calcular(num1, num2, "sumar") // Llama a la función calcular con la operación "sumar".
        }

        // Listener para el botón de restar.
        binding.btnRestar.setOnClickListener {
            val num1 = binding.numero1.text.toString()
            val num2 = binding.numero2.text.toString()
            calcular(num1, num2, "restar")
        }

        // Listener para el botón de multiplicar.
        binding.btnMultiplicar.setOnClickListener {
            val num1 = binding.numero1.text.toString()
            val num2 = binding.numero2.text.toString()
            calcular(num1, num2, "multiplicar")
        }

        // Listener para el botón de dividir.
        binding.btnDividir.setOnClickListener {
            val num1 = binding.numero1.text.toString()
            val num2 = binding.numero2.text.toString()
            calcular(num1, num2, "dividir")
        }
    }

    fun calcular(n1: String, n2: String, operacion: String) {
        val num1 = n1.toFloatOrNull() // Convierte el primer número a Float o null si no es válido.
        val num2 = n2.toFloatOrNull() // Convierte el segundo número a Float o null si no es válido.

        // Validaciones de entrada:
        if (num1 == null && num2 == null) {
            // Ambos números inválidos.
            Toast.makeText(this, getString(R.string.error_ambos_invalidos), Toast.LENGTH_SHORT).show()
            return
        } else if (num1 == null) {
            // Primer número inválido.
            Toast.makeText(this, getString(R.string.error_num1_invalido), Toast.LENGTH_SHORT).show()
            return
        } else if (num2 == null) {
            // Segundo número inválido.
            Toast.makeText(this, getString(R.string.error_num2_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        // Validación específica para división por cero.
        if (operacion == "dividir" && num2 == 0f) {
            Toast.makeText(this, getString(R.string.error_division_cero), Toast.LENGTH_SHORT).show()
            binding.textoresultadoanuncio2.text = null // Limpia el resultado.
            return
        }

        // Realiza la operación según el parámetro recibido.
        val resultado = when (operacion) {
            "sumar" -> num1 + num2
            "restar" -> num1 - num2
            "multiplicar" -> num1 * num2
            "dividir" -> num1 / num2
            else -> {} // Caso por defecto (no debería ocurrir).
        }

        // Muestra el resultado en el TextView correspondiente.
        binding.textoresultadoanuncio2.text = resultado.toString()
    }
}
