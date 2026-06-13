package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.service.BookRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books-ref/template")
public class BookRefTemplateController {
    @Autowired
    BookRefService bookRefService;
    @GetMapping
    public ResponseEntity<?> ObtenerTodos(){
        return ResponseEntity.ok(bookRefService.buscarTodos());
    }
    // Buscar libros por titulo
    @GetMapping("search/titulo")
    public ResponseEntity<?> ObtenerTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(bookRefService.buscarTitulo(titulo));
    }
    // Buscar libros por IdAutor
    @GetMapping("search/autor/{autorId}")
    public ResponseEntity<?> ObtenerAutor(@PathVariable String autorId){
        return ResponseEntity.ok(bookRefService.buscarAutor(autorId));
    }
    // Buscar libros por su Id
    @GetMapping("/{id}")
    public ResponseEntity<?> ObtenerLibroId(@PathVariable String id){
        return ResponseEntity.ok(bookRefService.buscarLibro(id));
    }
    // Buscar libro por autor especifico con LookUP
    @GetMapping("search/autor-lookup")
    public ResponseEntity<?> ObtenerAutorLook(@RequestParam String nombre){
        return ResponseEntity.ok(bookRefService.buscarAutorLook(nombre));
    }
}
