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
    BookRefRepository bookRefRepository;
    // CRUD
    // Obtener todos
    // GET /api/libros
    @GetMapping
    public ResponseEntity<List<BookRef>> ObtenerTodos(){
        return ResponseEntity.ok(bookRefRepository.findAll());
    }
    // Obtener uno
    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookRef>> ObtenerUno(@PathVariable String id){
        return ResponseEntity.ok(bookRefRepository.findById(id));
    }
    // Añadir uno
    // POST /api/libros/
    @PostMapping()
    public ResponseEntity<Optional<BookRef>> CrearLibro(@RequestBody BookRef autor){
        BookRef bookRef= bookRefRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(bookRef));
    }
    // Modificar uno
    // PUT /api/autores/
    @PutMapping("/{id}")
    public ResponseEntity<Optional<BookRef>> ModiLibro(@PathVariable String id, @RequestBody BookRef libro){
        if (bookRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        BookRef bookREFsave= bookRefRepository.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(bookREFsave));
    }
    // Eliminar uno
    // Delete /api/libros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliLibro(@PathVariable String id){
        if (!bookRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookRefRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // Obtener por IdLibro
    // GET /api/libros/id
    @GetMapping("/search/{id}")
    public ResponseEntity<List<BookRef>> ObtenerIdAutor(@PathVariable String id){
        return ResponseEntity.ok(bookRefRepository.findByAutoresId(id));
    }
    // Obtener por Año y Precio
    // GET /api/libros/search
    @GetMapping("/search/precio-anio")
    public ResponseEntity<List<BookRef>> ObtenerAnioPrecio(@RequestParam Double precio,@RequestParam Integer anio){
        return ResponseEntity.ok(bookRefRepository.buscarPorPrecioInferiorYAnioSuperior(precio,anio));
    }
    // Buscar libros mas baratos que x o mas antiguos a x año
    //
    @GetMapping("/search/precio-anioanti")
    public ResponseEntity<List<BookRef>> ObtenerAnioPrecioAnti(@RequestParam Double precio,@RequestParam Integer anio){
        return ResponseEntity.ok(bookRefRepository.buscarPorPrecioInferiorYAnioInferior(precio,anio));
    }
}
