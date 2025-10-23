package org.iesch.proyectobase.proyectobase.controller;

import org.iesch.proyectobase.proyectobase.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class productoscontroller {
    @Autowired
    Map<Long,Producto> productos;
    @GetMapping("/producto")
    /*public List<Producto> getTodos(){
        return productos;
    }*/
    public ResponseEntity<?> getTodos(){
        if (productos.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(productos);
        }
    }
}
