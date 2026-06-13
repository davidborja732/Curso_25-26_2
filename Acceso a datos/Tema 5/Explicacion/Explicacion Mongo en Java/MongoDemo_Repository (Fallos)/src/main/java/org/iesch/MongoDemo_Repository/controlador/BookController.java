package org.iesch.MongoDemo_Repository.controlador;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    // CRUD.

    // Obtener Todos
    @GetMapping
    public ResponseEntity<List<Book>> getallBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }
    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable String id){
        Optional<Book> book=bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    // Crear libro
    @PostMapping
    public ResponseEntity<Book> createBook (@RequestBody Book book){
        Book bookSave=bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // Actualizar Libro
    @PutMapping("/{id}")
    public ResponseEntity<Book> ActualizarBookByID(@PathVariable String id, @RequestBody Book book){
        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        book.set_id(id);
        Book updateBook=bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }
    // Borrar Libro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteBook(@PathVariable String id){
        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // /api/books/search/titulo?q=..... Buscar Por titulo
    @GetMapping("/search/titulo")
    public ResponseEntity<List<Book>> BuscarPorTitulo(@RequestParam String q){
        return ResponseEntity.ok(bookRepository.findByTituloContainingIgnoreCase(q));
    }
    // /api/books/search/categoria?cat=..... Buscar Por Categoria
    @GetMapping("/search/categoria")
    public ResponseEntity<List<Book>> BuscarPorcategoria(@RequestParam String cat){
        return ResponseEntity.ok(bookRepository.findByCategoriasContainingIgnoreCase(cat));
    }
    // /api/books/search/autor?aut=..... Buscar Por Autor
    @GetMapping("/search/autor")
    public ResponseEntity<List<Book>> BuscarPorAutor(@RequestParam String aut){
       return ResponseEntity.ok(bookRepository.findByAutoresNombreContainingIgnoreCase(aut)); // Forma Normal
       //return ResponseEntity.ok(bookRepository. buscarPorAutorNombre(aut)); //Forma Personalizada (Recomendable forma normla en vez de esta)
    }
    // /api/books/search/precio?min=.....&max=.... Buscar Por precios
    @GetMapping("/search/precio")
    public ResponseEntity<List<Book>> BuscarPorPrecio(@RequestParam Double min,@RequestParam Double max){
        return ResponseEntity.ok(bookRepository.findByPrecioBetween(min, max));
    }
    // /api/books/search/year?anio=...... Buscar Por año publiacion
    @GetMapping("/search/year")
    public ResponseEntity<List<Book>> BuscarPorPrecio(@RequestParam Integer anio){
        return ResponseEntity.ok(bookRepository.findByAnioPublicacionGreaterThan(anio));
    }
    // /api/books/search/precio?max=.....&anio=...
    @GetMapping("/search/precio")
    public ResponseEntity<List<Book>> BuscarPorPrecioAnioUsu(@RequestParam Double max,@RequestParam Integer anio){
        return ResponseEntity.ok(bookRepository.buscarPorPrecioAnio(max,anio));
    }
}
