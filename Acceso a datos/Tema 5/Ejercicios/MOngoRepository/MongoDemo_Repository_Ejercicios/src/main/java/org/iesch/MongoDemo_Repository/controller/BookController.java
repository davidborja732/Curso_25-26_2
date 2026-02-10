package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // CRUD.

    //GETALL
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks (){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    //GET_ONE
    @GetMapping ("/{id}")
    public ResponseEntity<Book> getBookByID (@PathVariable String id){
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Book> createBook (@RequestBody Book book){
        Book bookSave = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookSave);
    }



    //Update. Actualizar un libro Existente.
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book){
        if(!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
         Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);


    }

    //DELETE:

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id){
        if(!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * /api/books/search/titulo?q=.......
     */

    @GetMapping("/search/titulo")
    public ResponseEntity<List<Book>> buscarPorTitulo (@RequestParam String q){
        return ResponseEntity.ok(bookRepository.findByTituloContainingIgnoreCase(q));
    }

    /*
     * /api/books/search/categoria?cat=.......
     */
    @GetMapping("/search/categoria")
    public ResponseEntity<List<Book>> buscarPorCategoria (@RequestParam String cat){
        return ResponseEntity.ok(bookRepository.findByCategoriasIgnoreCase(cat));
    }

    /*
    * /api/books/search/autor?nombre=....
    * El nombre del autor está en un documento embebido.
     */
    @GetMapping("/search/autor")
    public ResponseEntity<List<Book>> buscarPorNobreAutor (@RequestParam String nombre){
        return ResponseEntity.ok(bookRepository.findByAutoresNombre(nombre));
    }

    /**
     * /api/books/search/precio?min=....&max=....
     */
    @GetMapping("search/precio")
    public ResponseEntity<List<Book>> buscarRangoPrecios(@RequestParam Double min, @RequestParam Double max){
        return ResponseEntity.ok(bookRepository.findByPrecioBetween(min, max));
    }

    /**
     * /api/books/search/recientes?anio=....
     */
    @GetMapping("search/recientes")
    public ResponseEntity<List<Book>> buscarRecientes(@RequestParam Integer anio){
        return ResponseEntity.ok(bookRepository.findByAnioPublicacionGreaterThan(anio));
    }




    /// Querys personalizadas.
    /*
     * /api/books/search/nativo/autor?nombre=....
     * El nombre del autor está en un documento embebido. y hacemos uso de @Query
     */
    @GetMapping("/search/nativo/autor")
    public ResponseEntity<List<Book>> buscarNativoPorNombreAutor (@RequestParam String nombre){
        return ResponseEntity.ok(bookRepository.buscarPorAutorNombre(nombre));
    }

    /*
     * /api/books/search/nativo/autor?nombre=....
     * El nombre del autor está en un documento embebido. y hacemos uso de @Query
     */
    @GetMapping("/search/nativo/precioAnio")
    public ResponseEntity<List<Book>> buscarprecioMenorAnioMayor (@RequestParam Double precio, @RequestParam Integer anio){
        return ResponseEntity.ok(bookRepository.buscarPorPrecioInferiorYanioMayor(precio, anio));
    }
    /*
     * /api/books/search/nativo/autor?nombre=....
     * El nombre del autor está en un documento embebido. y hacemos uso de @Query
     */
    @GetMapping("/search/nativo/autorcat")
    public ResponseEntity<List<Book>> buscarTituloCategoria (@RequestParam String titulo, @RequestParam String categoria){
        return ResponseEntity.ok(bookRepository.findByTituloAndCategoriasIgnoreCase(titulo,categoria));
    }



}
