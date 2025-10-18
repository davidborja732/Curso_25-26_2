package org.iesch.edadcanina

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity_Edad_Canina : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_edad_canina)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Aqui la pantalla está mostrada

        // 1º - Tomamos el control de todos los elementos de la parte de la UI
        val resultText = findViewById<TextView>(R.id.tvRespuesta)
        val calculateButton = findViewById<Button>(R.id.btnCalcular)
        val ageEdit = findViewById<EditText>(R.id.etEdad)
        // 2 - Los botones tienen la propiedad setOnClickListener al pulsarlo
        calculateButton.setOnClickListener {
            // Aqui va el codigo de lo que queremos hacer al pulsar el boton de Calcular
            val edadString = ageEdit.text.toString()

            if (edadString.isEmpty()){
                // No hacemos nada
                // 3 Me creo un mensaje de tipo Toast
                Toast.makeText( this, R.string.texto_alerta, Toast.LENGTH_LONG).show()
            } else {
                val edadInt = edadString.toInt()

                val dogAge = edadInt * 7

                val resultadoString = getString(R.string.resulta_format, dogAge)
                resultText.text = resultadoString
            }




        }

    }
}