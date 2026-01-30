package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresREF;
import org.iesch.ad.DocumentosReferenciados.repositorio.AutoresRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/autores")
public class AutorREFController {
    @Autowired
    AutoresRefRepository autoresRefRepository;
    // CRUD
    // Obtener todos
    // GET /api/autores
    @GetMapping
    public ResponseEntity<List<AutoresREF>> ObtenerTodos(){
        return ResponseEntity.ok(autoresRefRepository.findAll());
    }
    // Obtener uno
    // GET /api/autores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AutoresREF>> ObtenerUno(@PathVariable String id){
        return ResponseEntity.ok(autoresRefRepository.findById(id));
    }
    // Añadir uno
    // POST /api/autores/
    @PostMapping()
    public ResponseEntity<Optional<AutoresREF>> CrearAutor(@RequestBody AutoresREF autor){
        AutoresREF autoresREF=autoresRefRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(autoresREF));
    }
    // Modificar uno
    // PUT /api/autores/
    @PutMapping("/{id}")
    public ResponseEntity<Optional<AutoresREF>> ModiAutor(@PathVariable String id,@RequestBody AutoresREF autor){
        if (autoresRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        autor.setId(id);
        AutoresREF autoresREFsave=autoresRefRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(autoresREFsave));
    }

}
