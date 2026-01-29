package org.iesch.MongoDemo_Repository.repositorio;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    // ================== QUERY METHODS ===================

    /**
     * Buscar libros por titulo (ignorando Mayúsculas y minúsculas)
     */

    List<Book> findByTituloContainingIgnoreCase (String titulo);

    /**
     * Buscar libros por categoría
     * Como categorías es un Array, Spring data buscará si contiene el elemento
     */
    List<Book> findByCategoriasIgnoreCase (String categoria);


    /**
     * Buscar libros por el nombre de su autor.
     * Documento embebido
     */
    List<Book> findByAutoresNombre (String nombre);


    /**
     * Buscar libros que esten en un rango de precios.
     */
    List<Book> findByPrecioBetween (Double precioMin, Double precioMax);

    /**
     * Buscar los libros que se han publicado después de un año concreto
     */
    List<Book> findByAnioPublicacionGreaterThan(Integer anio);

    //==================== Query Personalizadas. ==========

    /**
     * Buscar por nombre de autor usando query mongo.
     */
    @Query("{'autores.nombre': {$regex: ?0, $options: 'i'}}")
    List<Book> buscarPorAutorNombre(String nombreAutor);

    /**
     * Buscar libros con un precio inferior a XXX y que se hayan publicado despues del año ZZZ
     */
    @Query("{'precio': {$lt: ?0 }, 'anioPublicacion': {$gte: ?1}}")
    List<Book> buscarPorPrecioInferiorYanioMayor(Double precio, Integer anio);

}
