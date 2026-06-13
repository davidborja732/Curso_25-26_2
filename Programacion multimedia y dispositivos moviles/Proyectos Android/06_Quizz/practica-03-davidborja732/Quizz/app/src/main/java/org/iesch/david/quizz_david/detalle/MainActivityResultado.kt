package org.iesch.david.quizz_david.detalle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.david.quizz_david.MainActivityPregunta
import org.iesch.david.quizz_david.databinding.ActivityResultadoBinding
import org.iesch.david.quizz_david.R

class MainActivityResultado : AppCompatActivity() {
    companion object {
        const val OPCION_KEY = "idrespuesta"
        const val RESULTADO_KEY = "resultado"
        const val INDICE_KEY = "indice"
    }

    private lateinit var binding: ActivityResultadoBinding
    private var estadoFinal = false
    private var indice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultado = intent.getBooleanExtra(RESULTADO_KEY, false)
        indice = intent.getIntExtra(INDICE_KEY, 0)

        if (resultado) {
            binding.tvBienmal.text = getString(R.string.resultado_correcto)
        } else {
            binding.tvBienmal.text = getString(R.string.resultado_incorrecto)
        }

        if (indice == 2) {
            binding.btResultado.text = getString(R.string.bt_resultado)
        } else {
            binding.btResultado.text = getString(R.string.resultado_siguiente)
        }

        binding.btResultado.setOnClickListener {
            if (indice == 2 && !estadoFinal) {
                binding.tvBienmal.text = getString(R.string.resultado_finusu)
                binding.btResultado.text = getString(R.string.resultado_reiniciar)
                estadoFinal = true
            } else if (indice == 2 && estadoFinal) {
                val intent = Intent(this, MainActivityPregunta::class.java)
                intent.putExtra("indice", 0)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivityPregunta::class.java)
                intent.putExtra("indice", indice + 1)
                startActivity(intent)
                finish()
            }
        }
    }
}

