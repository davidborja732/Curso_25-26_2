package org.iesch.proyectobase.proyectobase.Controlador;

import org.iesch.proyectobase.proyectobase.model.MiRespuesta;
import org.iesch.proyectobase.proyectobase.servicio.TiendaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TiendaController {
    @Autowired
    TiendaServicio tiendaServicio;
    @GetMapping("/obtener/{veces}")
    public ResponseEntity<MiRespuesta> obtener(@PathVariable int veces){
        MiRespuesta miRespuesta=tiendaServicio.obtener(veces);
        return  ResponseEntity.ok(miRespuesta);
    }
}