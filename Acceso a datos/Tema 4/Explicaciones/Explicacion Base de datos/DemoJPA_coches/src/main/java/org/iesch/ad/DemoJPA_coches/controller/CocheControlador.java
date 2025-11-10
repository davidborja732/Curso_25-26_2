package org.iesch.ad.DemoJPA_coches.controller;

import org.iesch.ad.DemoJPA_coches.modelo.Coche;
import org.iesch.ad.DemoJPA_coches.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/coches")
public class CocheControlador {
    @Autowired
    CocheService cocheService;
    @PostMapping
    public ResponseEntity<Coche> guardarcoche(@RequestBody Coche coche){
        Coche cocheguardado=cocheService.guardar(coche);
        return ResponseEntity.ok(cocheguardado);
    }
    @GetMapping
    public ResponseEntity<List<Coche>> obtenertodos(){
        return ResponseEntity.ok(cocheService.obtenerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Coche> obteneruno(@PathVariable Long id){
        return ResponseEntity.ok(cocheService.obtenerUno(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Coche> actualizar(@PathVariable Long id,@RequestBody Coche coche){
        return ResponseEntity.status(HttpStatus.CREATED).body(cocheService.actualizar(id,coche));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Coche> actualizar(@PathVariable Long id){
        Coche cocheBorrado=cocheService.borrar(id);
        if (cocheBorrado!=null){
            return ResponseEntity.ok(cocheBorrado);
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
