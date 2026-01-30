package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //Buscar libros por un titulo y categoria
    // GET /api/book/template/search/titucat?titu= &cat=
    @GetMapping("search/titucat")
    public ResponseEntity<List<Book>> buscarPortitucat(@RequestParam String titu,String cat){
        return ResponseEntity.ok(bookService.findByTituCat(titu,cat));
    }
    //Buscar libros a traves de multiples categorias
    // POST /api/book/template/search/categoriasmultiples
    @PostMapping("search/categoriasmultiples")
    public ResponseEntity<List<Book>> buscarPorcategoriasmultiples(@RequestBody List<String> lista){
        return ResponseEntity.ok(bookService.findByCategoriasMultiples(lista));
    }
    //Buscar por precio maximo ordenado por año descendiente
    // GET /api/book/template/search/precioorden?precio=
    @GetMapping("search/precioorden")
    public ResponseEntity<List<Book>> buscarPorprecioorden(@RequestParam Double precio){
        return ResponseEntity.ok(bookService.findByPrecioOrden(precio));
    }
    //Buscar libros con mas de un autor
    // GET /api/book/template/search/multiplesautores
    @GetMapping("search/multiplesautores")
    public ResponseEntity<List<Book>> buscarPormultiplesautores(){
        return ResponseEntity.ok(bookService.findByMultiplesAutores());
    }
    // Contar libros por categoria
    // GET /api/book/template/search/libroscategoria?cat=
    @GetMapping("search/libroscategoria")
    public ResponseEntity<Long> buscarPorLibrosCategoria(@RequestParam String cat){
        return ResponseEntity.ok(bookService.findByLibrosCategoria(cat));
    }

}
