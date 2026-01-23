package org.iesch.MongoDemo_Repository.repositorio;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    // QUERY METHODS
    //Buscar libros por titulo y que ademas ignore mayusculas y minusculas
    List<Book> findByTituloContainingIgnoreCase (String titulo);
    //Buscar libros por categoria y que ademas ignore mayusculas y minusculas
    List<Book> findByCategoriasContainingIgnoreCase (String titulo);
    //Buscar libros por Autor nombre y que ademas ignore mayusculas y minusculas
    List<Book> findByAutoresNombreContainingIgnoreCase (String titulo);
    //Buscar libros que su precio este en un rango de precio
    List<Book> findByPrecioBetween (Double min,Double max);
    //Buscar libros que su año de lanzamiento sea menor a aun año
    List<Book> findByAnioPublicacionGreaterThan (Integer year);




    // CONSULTAS PERSONALIZADAS
    // Buscar Por nombre de autor usando query mongo
    @Query("{'autores.nombre':{$regex: '?0'}}")
    List<Book> buscarPorAutorNombre(String nombreAutor);
    // Buscar Por libros en un rango ademas de que el año de publicacion sea mayor al pasado
    @Query("{'precio':{$lt: ?0},'anioPublicacion':{$gte: ?1}}, ")
    List<Book> buscarPorPrecioAnio(Double max,Integer anio);
}
