package org.iesch.intermedio

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.iesch.intermedio.adapter.AndroidVersionAdapter
import org.iesch.intermedio.databinding.ActivityMainBinding
import org.iesch.intermedio.provider.AndroidVersionProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var adapter: AndroidVersionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initRecyclerview()

    }

    private fun initRecyclerview() {
        val versionesandroid= AndroidVersionProvider.androidVersionsList
        adapter= AndroidVersionAdapter(versionesandroid)
        //configurar recyclerview
        binding.rvVersiones.layoutManager = LinearLayoutManager(this)
        binding.rvVersiones.adapter=adapter
    }
}