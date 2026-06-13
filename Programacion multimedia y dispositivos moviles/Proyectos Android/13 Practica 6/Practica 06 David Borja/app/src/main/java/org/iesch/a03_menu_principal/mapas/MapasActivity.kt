package org.iesch.a03_menu_principal.mapas
// Paquete donde se encuentra la actividad de mapas.

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import org.practica_6.david.R
import org.practica_6.david.databinding.ActivityMapasBinding
// Importaciones necesarias para Mapbox, UI y binding.

class MapasActivity : AppCompatActivity() {
    // Actividad que muestra un mapa con varios puntos usando Mapbox.

    private var chomonViewAnnotation: View? = null
    // Variable opcional para futuras anotaciones de vista (no usada en este código).

    private lateinit var binding: ActivityMapasBinding
    // Binding para acceder a las vistas del layout.

    private lateinit var mapView: MapView
    // Referencia al MapView de Mapbox.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa diseño edge-to-edge.
        binding = ActivityMapasBinding.inflate(layoutInflater) // Infla el layout con ViewBinding.
        setContentView(binding.root) // Establece el layout como contenido.

        // Ajusta márgenes según barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Iniciamos el mapa.
        mapView = binding.mapview

        // Definimos las coordenadas de varios institutos.
        val iesChomon = Point.fromLngLat(-1.097681, 40.327509)
        val iesSantaEmerenciana = Point.fromLngLat(-1.106298, 40.333217)
        val iesFrancesAranda = Point.fromLngLat(-1.109779, 40.351472)
        val iesVegaTuria = Point.fromLngLat(-1.1091475323669322, 40.34083221892887)

        // Configuramos el mapa y el estilo.
        mapView.mapboxMap.apply {
            // Cargamos el estilo del mapa (calles de Mapbox).
            loadStyle(Style.MAPBOX_STREETS) { style ->
                // Configuramos la ubicación inicial de la cámara.
                setCamera(
                    CameraOptions.Builder()
                        .center(iesVegaTuria) // Centra el mapa en Vega Turia.
                        .zoom(13.0) // Nivel de zoom inicial.
                        .build()
                )

                // Cargar y añadir la imagen del marcador al estilo.
                val drawable = ContextCompat.getDrawable(this@MapasActivity, R.drawable.marcador_rojo)
                val bitmap = createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)

                style.addImage("custom-marker", bitmap) // Añade la imagen al estilo con el nombre "custom-marker".

                // Crear el gestor de anotaciones (marcadores).
                val annotationApi = mapView.annotations
                val pointAnnotationManager = annotationApi.createPointAnnotationManager()

                // Definimos cada marcador con su punto y el icono personalizado.
                val markerChomon = PointAnnotationOptions()
                    .withPoint(iesChomon)
                    .withIconImage("custom-marker")
                    .withIconSize(2.0)

                val markerVega = PointAnnotationOptions()
                    .withPoint(iesVegaTuria)
                    .withIconImage("custom-marker")
                    .withIconSize(2.0)

                val markerFrances = PointAnnotationOptions()
                    .withPoint(iesFrancesAranda)
                    .withIconImage("custom-marker")
                    .withIconSize(2.0)

                val markerSanta = PointAnnotationOptions()
                    .withPoint(iesSantaEmerenciana)
                    .withIconImage("custom-marker")
                    .withIconSize(2.0)

                // Añadimos los marcadores al mapa.
                pointAnnotationManager.create(markerChomon)
                pointAnnotationManager.create(markerFrances)
                pointAnnotationManager.create(markerSanta)
                pointAnnotationManager.create(markerVega)
            }
        }
    }
}
