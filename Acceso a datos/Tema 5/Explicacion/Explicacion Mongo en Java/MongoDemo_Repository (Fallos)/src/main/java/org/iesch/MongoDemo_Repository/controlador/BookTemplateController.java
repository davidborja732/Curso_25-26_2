package org.iesch.MongoDemo_Repository.controlador;

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
}
