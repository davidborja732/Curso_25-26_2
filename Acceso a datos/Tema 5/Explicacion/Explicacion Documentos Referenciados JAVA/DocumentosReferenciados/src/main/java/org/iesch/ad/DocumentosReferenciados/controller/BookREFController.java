package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.BookRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/libros")
public class BookREFController {
    @Autowired
    BookRefRepository autoresRefRepository;
    // CRUD
    // Obtener todos
    // GET /api/autores
    @GetMapping
    public ResponseEntity<List<BookRef>> ObtenerTodos(){
        return ResponseEntity.ok(autoresRefRepository.findAll());
    }
    // Obtener uno
    // GET /api/autores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookRef>> ObtenerUno(@PathVariable String id){
        return ResponseEntity.ok(autoresRefRepository.findById(id));
    }
    // Añadir uno
    // POST /api/autores/
    @PostMapping()
    public ResponseEntity<Optional<BookRef>> CrearAutor(@RequestBody BookRef autor){
        BookRef bookRef=autoresRefRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(bookRef));
    }
    // Modificar uno
    // PUT /api/autores/
    @PutMapping("/{id}")
    public ResponseEntity<Optional<BookRef>> ModiAutor(@PathVariable String id, @RequestBody BookRef libro){
        if (autoresRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        BookRef bookREFsave=autoresRefRepository.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(bookREFsave));
    }

}
