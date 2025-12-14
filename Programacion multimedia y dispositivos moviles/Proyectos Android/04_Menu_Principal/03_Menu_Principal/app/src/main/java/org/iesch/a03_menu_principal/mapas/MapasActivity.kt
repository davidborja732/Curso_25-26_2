package org.iesch.a03_menu_principal.mapas

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityMapasBinding
import kotlin.math.PI

class MapasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapasBinding
    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMapasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bitmap= BitmapFactory.decodeResource(resources,R.drawable.marcador_rojo)
        // Iniciamos el mapa
        mapView=binding.mapview
        // Necesitamos el token
        val mapboxToken=getString(R.string.mapbox_access_token)
        // Configuramos el mapa y su estilo
        mapView.mapboxMap.apply {
            // cargamos el estilo del mapa
            loadStyle(Style.SATELLITE_STREETS){
                // Configuramos la ubicacion inicial del mapa
                style ->
                setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(-1.097681, 40.327509))
                        .pitch(0.0)
                        .zoom(16.0)
                        .bearing(0.0)
                        .build()
                )
            }
        }
        anadeMarcador()


    }

    private fun anadeMarcador() {
        /*val annotationApi=mapView.annotations
        val pointAnnotationManager=annotationApi.createAnnotationManager()
        val pointAnnotationOptions= PointAnnotationOptions()
            .withPoint(Point.fromLngLat(-1.097681, 40.327509))
            .withIconImage(bit)*/

    }
}