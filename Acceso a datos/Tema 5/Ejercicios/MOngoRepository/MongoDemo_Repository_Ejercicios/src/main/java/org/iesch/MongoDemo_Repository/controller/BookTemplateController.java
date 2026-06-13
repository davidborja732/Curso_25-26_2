package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book/template")
public class BookTemplateController {
    @Autowired
    BookService bookService;

    /*Consultas Basicas*/
    //Buscar libros por titulo
    // GET /api/book/template/search/titulo?q=titulo
    @GetMapping("search/titulo")
    public ResponseEntity<List<Book>> buscarPorTitulo(@RequestParam String q){
        return ResponseEntity.ok(bookService.findByTituloConteiningIgnoreCase(q));
    }
    //Buscar libros por titulo
    // GET /api/book/template/search/categoria?cat=categoria
    @GetMapping("search/categoria")
    public ResponseEntity<List<Book>> buscarPorcategoria(@RequestParam String cat){
        return ResponseEntity.ok(bookService.findByCategoriaConteiningIgnoreCase(cat));
    }
    //Buscar libros por autor
    // GET /api/book/template/search/autor?aut=autor
    @GetMapping("search/autor")
    public ResponseEntity<List<Book>> buscarPorAutor(@RequestParam String aut){
        return ResponseEntity.ok(bookService.findByAutorConteiningIgnoreCase(aut));
    }
    //Buscar libros por rango de precio
    // GET /api/book/template/search/precio?min=0&max=1
    @GetMapping("search/precio")
    public ResponseEntity<List<Book>> buscarPorPrecio(@RequestParam Double min,Double max){
        return ResponseEntity.ok(bookService.findByPrecioRango(min,max));
    }
    //Buscar libros mas recientes a un año
    // GET /api/book/template/search/recientes?anio=2002
    @GetMapping("search/recientes")
    public ResponseEntity<List<Book>> buscarPorAnio(@RequestParam int anio){
        return ResponseEntity.ok(bookService.findByAnio(anio));
    }
    //Buscar libros por un precio inferior y que se hayan publicados despues de un año
    // GET /api/book/template/search/infeanio?infe=20&anio=2002
    @GetMapping("search/infeanio")
    public ResponseEntity<List<Book>> buscarPorinfeanio(@RequestParam Double infe,int anio){
        return ResponseEntity.ok(bookService.findByInfeanio(infe,anio));
    }
}
